import threading
import time
from pymodbus.server.sync import StartTcpServer
from pymodbus.datastore import ModbusSequentialDataBlock, ModbusSlaveContext, ModbusServerContext
from pymodbus.client.sync import ModbusTcpClient as ModbusClient
from scapy.all import sniff, TCP
import matplotlib.pyplot as plt
import numpy as np
import matplotlib
matplotlib.use('TkAgg')
class ModbusSystem:
    def __init__(self):
        self.context = self._create_context()
        self.client = ModbusClient('localhost', port=5020)
        self.sniffing = True
        self.data_points = []
        self.lock = threading.Lock()

    def _create_context(self):
        store = ModbusSlaveContext(
            di=ModbusSequentialDataBlock(0, [1]*100),
            co=ModbusSequentialDataBlock(0, [0]*100),
            hr=ModbusSequentialDataBlock(0, [3]*100),
            ir=ModbusSequentialDataBlock(0, [4]*100))
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
                with self.lock:
                    self.data_points.append(rr.registers[0])
                    if len(self.data_points) > 100:
                        self.data_points.pop(0)
            time.sleep(1)

    def packet_callback(self, pkt):
        if pkt.haslayer(TCP) and pkt[TCP].dport == 5020:
            modbus_data = pkt.getlayer(TCP).payload.load
            print(f"Captured Modbus data: {modbus_data.hex()}")

    def start_sniffer(self):
        print("Starting network sniffer...")
        sniff(filter="tcp port 5020", prn=self.packet_callback, stop_filter=lambda x: not self.sniffing)

    def update_plot(self):
        fig, ax = plt.subplots()
        line, = ax.plot([], [], 'r-')
        ax.set_xlim(0, 100)
        ax.set_ylim(0, 10)
        ax.grid(True)
        xdata, ydata = [], []

        def init():
            line.set_data(xdata, ydata)
            return line,

        def run(frame):
            # Update the data.
            t = time.time() - start_time
            xdata.append(t)
            with self.lock:
                ydata.append(self.data_points[-1] if self.data_points else 0)
            xdata = xdata[-100:]
            ydata = ydata[-100:]

            xmin, xmax = ax.get_xlim()

            if t >= xmax:
                ax.set_xlim(xmin, 2*xmax)
                ax.figure.canvas.draw()

            line.set_data(xdata, ydata)

            return line,

        from matplotlib.animation import FuncAnimation
        ani = FuncAnimation(fig, run, init_func=init, interval=1000, blit=False, cache_frame_data=False)
        plt.show()

    def run(self):
        server_thread = threading.Thread(target=self.start_server)
        client_thread = threading.Thread(target=self.start_client)
        sniffer_thread = threading.Thread(target=self.start_sniffer)

        server_thread.daemon = True
        client_thread.daemon = True
        sniffer_thread.daemon = True

        global start_time
        start_time = time.time()

        server_thread.start()
        client_thread.start()
        sniffer_thread.start()

        # Move plot to main thread
        self.update_plot()

        try:
            while True:
                pass
        except KeyboardInterrupt:
            self.sniffing = False
            print("Stopping all threads...")

if __name__ == "__main__":
    system = ModbusSystem()
    system.run()



