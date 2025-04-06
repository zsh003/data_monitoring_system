# modbus: 2.5.2
import threading
from pymodbus.server.sync import StartTcpServer
from pymodbus.datastore import ModbusSequentialDataBlock, ModbusSlaveContext, ModbusServerContext
from pymodbus.client.sync import ModbusTcpClient as ModbusClient
from scapy.all import sniff, TCP

class ModbusSystem:
    def __init__(self):
        self.context = self._create_context()
        self.client = ModbusClient('localhost', port=5020)
        self.sniffing = True

    def _create_context(self):
        store = ModbusSlaveContext(
            di=ModbusSequentialDataBlock(0, [1] * 100),  # 所有离散输入初始化为1
            co=ModbusSequentialDataBlock(0, [1, 0] * 50),  # 线圈交替设置为1和0
            hr=ModbusSequentialDataBlock(0, list(range(100))),  # 保持寄存器从0到99
            ir=ModbusSequentialDataBlock(0, [5] * 100))  # 输入寄存器全部设置为5
        context = ModbusServerContext(slaves=store, single=True)
        return context

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

    def packet_callback(self, pkt):
        if pkt.haslayer(TCP) and pkt[TCP].dport == 5020:
            modbus_data = pkt.getlayer(TCP).payload.load
            print(f"Captured Modbus data: {modbus_data.hex()}")

    def start_sniffer(self):
        print("Starting network sniffer...")
        sniff(filter="tcp port 5020", prn=self.packet_callback, stop_filter=lambda x: not self.sniffing)

    def run(self):
        server_thread = threading.Thread(target=self.start_server)
        client_thread = threading.Thread(target=self.start_client)
        sniffer_thread = threading.Thread(target=self.start_sniffer)

        server_thread.daemon = True
        client_thread.daemon = True
        sniffer_thread.daemon = True

        server_thread.start()
        client_thread.start()
        sniffer_thread.start()

        try:
            while True:
                pass
        except KeyboardInterrupt:
            self.sniffing = False
            print("Stopping all threads...")

if __name__ == "__main__":
    system = ModbusSystem()
    system.run()



