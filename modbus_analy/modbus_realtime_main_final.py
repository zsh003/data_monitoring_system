import numpy as np
import datetime
from datetime import datetime as dt, timedelta
from pymodbus.server.sync import StartTcpServer
from pymodbus.datastore import ModbusSequentialDataBlock, ModbusSlaveContext, ModbusServerContext
from pymodbus.client.sync import ModbusTcpClient
import pymysql
import random
import threading
import time
import sys
from concurrent.futures import ThreadPoolExecutor
import subprocess

class SensorDataGenerator:
    def __init__(self, plc_id, seed=None):
        self.plc_id = plc_id
        np.random.seed(seed)
        random.seed(seed)
        self.last_temp = 20
        self.last_speed = 5
        self.last_light = 50
        self.last_angle = 180

    def generate_next_data(self, current_time):
        """生成下一个时间点的传感器数据"""
        hour = current_time.hour + current_time.minute / 60

        # 温度生成
        temp_base = 10 + 8 * np.sin(2 * np.pi * (hour - 6) / 24)
        temp_noise = np.random.normal(0, 1.5)
        self.last_temp = np.clip(temp_base + temp_noise, -5, 35)

        # 速度生成
        if random.random() < 0.1:
            delta = np.random.normal(2.0, 0.8)
        else:
            delta = np.random.normal(0.5, 0.3)
        self.last_speed = np.clip(self.last_speed + delta, 0, 25)

        # 亮度生成
        light_base = 50 + 30 * np.sin(np.pi * (hour - 6) / 12)  # 增加偏移量
        light_noise = np.random.normal(0, 10)
        self.last_light = np.clip(light_base + light_noise, 0, 100)

        # 角度生成
        angle_delta = np.random.normal(0, 3)
        angle_delta += (180 - self.last_angle) * 0.05
        self.last_angle = (self.last_angle + angle_delta) % 360

        return {
            'temperature': self.last_temp,
            'speed': self.last_speed,
            'light': self.last_light,
            'angle': self.last_angle
        }

class ModbusServer:
    def __init__(self, plc_count=4):
        self.plc_count = plc_count
        self.generators = {
            i+1: SensorDataGenerator(i+1, seed=999+i)
            for i in range(plc_count)
        }
        self.current_time = dt.now()  # 当前系统时间

        # 模拟PLC IP地址
        self.plc_ips = {i+1: f"192.168.2.{i+1}" for i in range(plc_count)}

        # 初始化数据存储
        self.store = ModbusSlaveContext(
            hr=ModbusSequentialDataBlock(0, [0] * 100),  # 用于存储传感器数据
            ir=ModbusSequentialDataBlock(0, [0] * 100),
            di=ModbusSequentialDataBlock(0, [0] * 100),
            co=ModbusSequentialDataBlock(0, [0] * 100)
        )
        self.context = ModbusServerContext(slaves=self.store, single=True)
        self.executor = ThreadPoolExecutor(max_workers=10)  # 线程池限制最大线程数

    def update_single_plc(self, plc_id):
        """更新单个PLC的数据"""
        data = self.generators[plc_id].generate_next_data(self.current_time)
        base_address = (plc_id - 1) * 4
        self.store.setValues(3, base_address, [
            int(data['temperature'] * 100),
            int(data['speed'] * 100),
            int(data['light'] * 100),
            int(data['angle'] * 100)
        ])

    def update_data(self):
        """每秒更新所有PLC的数据"""
        while True:
            for plc_id in range(1, self.plc_count + 1):
                self.executor.submit(self.update_single_plc, plc_id)
            self.current_time += timedelta(seconds=1)
            time.sleep(1)  # 每秒更新一次数据

    def run(self):
        # 启动数据更新线程
        update_thread = threading.Thread(target=self.update_data)
        update_thread.daemon = True
        update_thread.start()

        # 启动Modbus服务器
        print("Starting Modbus TCP server on 192.168.2.1-4:5020")
        StartTcpServer(self.context, address=("0.0.0.0", 5020))

class ModbusClient:
    def __init__(self, host='localhost', port=5020):
        self.client = ModbusTcpClient(host, port=port, timeout=5)  # 增加超时时间
        self.db = pymysql.connect(
            host="localhost",
            user="plc",
            password="123456",
            database="plc"
        )
        self.cursor = self.db.cursor()

    def read_plc_data(self, plc_id):
        """读取指定PLC的所有传感器数据"""
        base_address = (plc_id - 1) * 4
        retries = 3  # 最大重试次数
        for _ in range(retries):
            try:
                response = self.client.read_holding_registers(base_address, 4, unit=1)
                if not response.isError():
                    print(f"PLC {plc_id} 寄存器地址: {base_address}, 数据: {response.registers}")
                    return {
                        'temperature': response.registers[0] / 100.0,
                        'speed': response.registers[1] / 100.0,
                        'light': response.registers[2] / 100.0,
                        'angle': response.registers[3] / 100.0
                    }
                else:
                    print(f"PLC {plc_id} 数据读取失败，正在重试...")
            except Exception as e:
                print(f"PLC {plc_id} 连接异常: {e}, 正在重试...")
                time.sleep(1)  # 等待1秒后重试

        print(f"PLC {plc_id} 数据读取失败，跳过本次读取")
        return None

    def save_to_database(self, plc_id, data, timestamp):
        """将数据保存到MySQL数据库"""
        sql = """INSERT INTO sensor_data 
                (plc_id, time, temperature, speed, light, angle) 
                VALUES (%s, %s, %s, %s, %s, %s)"""
        values = (
            plc_id,
            timestamp,
            data['temperature'],
            data['speed'],
            data['light'],
            data['angle']
        )

        try:
            self.cursor.execute(sql, values)
            self.db.commit()
        except pymysql.Error as err:
            print(f"数据库错误: {err}")
            self.db.rollback()

    def run(self):
        """运行客户端，持续读取数据并存储"""
        print("开始从Modbus服务器读取数据并存储到数据库...")

        while True:
            current_time = dt.now()

            for plc_id in range(1, 5):  # 读取4个PLC的数据
                data = self.read_plc_data(plc_id)
                if data:
                    self.save_to_database(plc_id, data, current_time)
                    print(f"已保存 PLC {plc_id} 数据: {data}")

            time.sleep(1)  # 每秒读取一次数据

    def close(self):
        """关闭连接"""
        self.client.close()
        self.cursor.close()
        self.db.close()

def run_server():
    server = ModbusServer()
    server.run()

def run_client():
    client = ModbusClient()
    try:
        client.run()
    except KeyboardInterrupt:
        print("\n正在关闭连接...")
        client.close()
        print("程序已终止")

def configure_virtual_ips():
    """配置虚拟IP地址（需要管理员权限）"""
    try:
        interface_name = "以太网"
        for i in range(1,5):
            ip = f"192.168.2.{i}"
            subprocess.check_call(
                f'netsh interface ipv4 add address "Ethernet" {ip} 255.255.255.0',
                shell=True
            )
        print("虚拟IP地址配置成功：192.168.2.1-4")
    except subprocess.CalledProcessError as e:
        print("IP地址配置失败，请以管理员权限运行程序\n")
        sys.exit(1)

if __name__ == "__main__":
    # 配置虚拟IP地址
    configure_virtual_ips()

    try:
        # 启动服务器线程
        server_thread = threading.Thread(target=run_server)
        server_thread.daemon = True
        server_thread.start()

        # 等待服务器启动
        time.sleep(3)

        # 启动客户端
        print("正在启动系统...")
        print("服务器已启动，开始运行客户端...")
        run_client()
    finally:
        # 清理虚拟IP
        for i in range(1,5):
            ip = f"192.168.2.{i}"
            subprocess.call(
                f'netsh interface ipv4 delete address "Ethernet" {ip}',
                shell=True
            )