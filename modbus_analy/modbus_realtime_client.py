from pymodbus.client.sync import ModbusTcpClient
import pymysql
from datetime import datetime
import time

class ModbusClient:
    def __init__(self, host='localhost', port=5020):
        self.client = ModbusTcpClient(host, port=port)
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
        response = self.client.read_holding_registers(base_address, 4, unit=1)
        
        if response.isError():
            print(f"Error reading PLC {plc_id}")
            return None
            
        return {
            'temperature': response.registers[0] / 100.0,
            'speed': response.registers[1] / 100.0,
            'light': response.registers[2] / 100.0,
            'angle': response.registers[3] / 100.0
        }
    
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
            current_time = datetime.now()
            
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

if __name__ == "__main__":
    client = ModbusClient()
    try:
        client.run()
    except KeyboardInterrupt:
        print("\n正在关闭连接...")
        client.close()
        print("程序已终止") 