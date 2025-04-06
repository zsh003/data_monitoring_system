from pymodbus.client.sync import ModbusTcpClient

def run_client():
    client = ModbusTcpClient('127.0.0.1', port=5020)
    response = client.read_holding_registers(0, 10, unit=1)
    print(response.registers)
    client.close()

if __name__ == "__main__":
    run_client()