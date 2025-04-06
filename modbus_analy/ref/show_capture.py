# 修复后的完整导入部分
import threading
import random
import math
from collections import deque
from pymodbus.server.sync import StartTcpServer
from pymodbus.datastore import (  # 关键修复点
    ModbusSequentialDataBlock,
    ModbusSlaveContext,
    ModbusServerContext
)
from pymodbus.client.sync import ModbusTcpClient as ModbusClient
from scapy.all import sniff, TCP
import matplotlib.pyplot as plt
import matplotlib.animation as animation


class DataGenerator:
    def __init__(self, context):
        self.context = context
        self.running = True
        self._init_parameters()

    def _init_parameters(self):
        """初始化各种数据模式的参数"""
        self.counter = 0
        self.sine_phase = 0
        self.random_step = 5

    def generate_data(self):
        """生成并更新所有数据模式"""
        self._update_sine_wave()
        self._update_random_walk()
        self._update_sawtooth()
        self._update_step_changes()
        self.counter += 1

    def _update_sine_wave(self):
        """正弦波生成（地址0-9）"""
        slave = self.context[0]
        values = slave.store['hr'].values
        for i in range(10):
            values[i] = int(50 * math.sin(self.sine_phase + i * 0.2) + 50)
        self.sine_phase += 0.1

    def _update_random_walk(self):
        """随机游走（地址10-19）"""
        slave = self.context[0]
        values = slave.store['hr'].values
        for i in range(10, 20):
            values[i] = min(max(values[i] + random.randint(-2, 2), 0), 100)

    def _update_sawtooth(self):
        """锯齿波（地址20-29）"""
        slave = self.context[0]
        values = slave.store['hr'].values
        base = self.counter % 100
        for i in range(20, 30):
            values[i] = (base + (i - 20) * 2) % 100

    def _update_step_changes(self):
        """阶跃变化（地址30-39）"""
        slave = self.context[0]
        values = slave.store['hr'].values
        if self.counter % 50 == 0:
            self.random_step = random.randint(0, 100)
        for i in range(30, 40):
            values[i] = self.random_step


class Visualization:
    def __init__(self, context, client):
        self.context = context
        self.client = client
        self.max_len = 50  # 每个寄存器显示的数据点数
        self.buffers = {
            'sine': deque([0] * self.max_len, maxlen=self.max_len),
            'random': deque([0] * self.max_len, maxlen=self.max_len),
            'sawtooth': deque([0] * self.max_len, maxlen=self.max_len),
            'step': deque([0] * self.max_len, maxlen=self.max_len)
        }

        # 初始化图表
        self.fig, self.axs = plt.subplots(4, 1, figsize=(10, 8))
        self.lines = {
            'sine': self.axs[0].plot([], [])[0],
            'random': self.axs[1].plot([], [])[0],
            'sawtooth': self.axs[2].plot([], [])[0],
            'step': self.axs[3].plot([], [])[0]
        }

        # 配置图表参数
        for ax, title in zip(self.axs, ['Sine Wave', 'Random Walk', 'Sawtooth', 'Step Changes']):
            ax.set_ylim(0, 100)
            ax.set_xlim(0, self.max_len)
            ax.set_title(title)
            ax.grid(True)

    def update_plot(self, frame):
        """更新图表数据"""
        # 从保持寄存器获取最新数据
        try:
            slave = self.context[0]
            values = slave.store['hr'].values
            self.buffers['sine'].append(values[0])
            self.buffers['random'].append(values[10])
            self.buffers['sawtooth'].append(values[20])
            self.buffers['step'].append(values[30])
        except Exception as e:
            print(f"Error reading data: {e}")
            return self.lines.values()

        # 更新各条曲线
        for key in self.lines:
            self.lines[key].set_data(range(self.max_len), self.buffers[key])
        return self.lines.values()

    def start(self):
        """启动可视化"""
        ani = animation.FuncAnimation(
            self.fig,
            self.update_plot,
            interval=200,
            blit=True,
            cache_frame_data=False
        )
        plt.show()


class ModbusSystem:
    def __init__(self):
        self.context = self._create_context()
        self.client = ModbusClient('localhost', port=5020)
        self.sniffing = True
        self.data_gen = DataGenerator(self.context)
        self.visualization = Visualization(self.context, self.client)

    # 必须保留原有的服务端/客户端/嗅探器方法定义
    def start_server(self):
        print("Starting Modbus server...")
        StartTcpServer(context=self.context, address=("localhost", 5020))

    def start_client(self):
        print("Starting Modbus client...")
        if not self.client.connect():
            print("Failed to connect to the modbus server")
            return
        while True:
            rr = self.client.read_holding_registers(address=0, count=10, unit=1)
            if rr.isError():
                print(f"Modbus error: {rr}")
            else:
                print(f"Registers read: {rr.registers}")

    def start_sniffer(self):
        print("Starting network sniffer...")
        sniff(filter="tcp port 5020", prn=self.packet_callback, stop_filter=lambda x: not self.sniffing)

    # 确保保留原有其他方法
    def packet_callback(self, pkt):
        if pkt.haslayer(TCP) and pkt[TCP].dport == 5020:
            modbus_data = pkt.getlayer(TCP).payload.load
            print(f"Captured Modbus data: {modbus_data.hex()}")

    # ... 其他方法保持原样 ...

if __name__ == "__main__":
    system = ModbusSystem()
    system.run()