from pymodbus.server.sync import StartTcpServer
from pymodbus.datastore import ModbusSequentialDataBlock, ModbusSlaveContext, ModbusServerContext

def run_server():
    # 创建数据存储区
    # 初始化一些值
    store = ModbusSlaveContext(
        di=ModbusSequentialDataBlock(0, [1]*100),  # 所有离散输入初始化为1
        co=ModbusSequentialDataBlock(0, [1, 0]*50), # 线圈交替设置为1和0
        hr=ModbusSequentialDataBlock(0, list(range(100))), # 保持寄存器从0到99
        ir=ModbusSequentialDataBlock(0, [5]*100)    # 输入寄存器全部设置为5
    )

    context = ModbusServerContext(slaves=store, single=True)

    # 配置服务器
    address = ("localhost", 5020)
    print(f"Starting Modbus TCP server on {address[0]}:{address[1]}")
    
    # 启动服务器
    StartTcpServer(context, address=address)

if __name__ == "__main__":
    run_server()

