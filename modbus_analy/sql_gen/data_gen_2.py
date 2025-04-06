import numpy as np
import datetime
from datetime import datetime as dt, timedelta
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
import json
import random

# ========================
# 参数配置
# ========================
random_seed = 999        # 可修改种子值进行测试
PLCs = [                # 可配置多个PLC设备
    {"description": {"location": "Wind Farm A", "type": "Offshore 2.5MW"}}
]

# ========================
# 1. 数据生成函数
# ========================
def generate_sensor_data(time_points, seed=None):
    """生成四类传感器数据，包含合理的变化模式"""
    np.random.seed(seed)
    random.seed(seed)
    n = len(time_points)
    
    # 生成小时序列（带小数）
    hours = np.array([t.hour + t.minute/60 for t in time_points])
    days = np.array([t.timestamp() for t in time_points]) / (24*3600)

    # 温度（昼夜变化 + 日间随机波动)
    temp_base = 10 + 8 * np.sin(2 * np.pi * (hours - 6)/24)  # 6AM开始升温
    temp_noise = np.random.normal(0, 1.5, n)
    temperature = np.clip(temp_base + temp_noise, -5, 35)

    # 速度（基于马尔可夫链的风速状态模拟）
    speed = np.zeros(n)
    state = 0  # 0-无风，1-小风，2-大风
    speed[0] = 0.5
    for i in range(1, n):
        # 状态转移概率
        if state == 0:
            if random.random() < 0.02: state = 1
        elif state == 1:
            if random.random() < 0.05: state = 0
            elif random.random() < 0.1: state = 2
        else:
            if random.random() < 0.15: state = 1
        
        # 根据状态生成速度变化
        if state == 0:
            delta = np.random.normal(-0.1, 0.1)
        elif state == 1:
            delta = np.random.normal(0.5, 0.3)
        else:
            delta = np.random.normal(2.0, 0.8)
        
        speed[i] = speed[i-1] + delta
        speed[i] = np.clip(speed[i], 0, 25)

    # 亮度（日照模拟 + 天气影响）
    light_base = 80 * np.sin(np.pi * (hours - 6)/12)  # 6AM-6PM有光
    light_weather = np.random.normal(0, 15, n)
    light = np.clip(light_base + light_weather, 0, 100)

    # 水平角度（随机游走 + 偏向修正）
    angle = np.zeros(n)
    angle[0] = 180
    for i in range(1, n):
        delta = np.random.normal(0, 3)
        # 向180度方向回归的倾向
        delta += (180 - angle[i-1]) * 0.05
        angle[i] = angle[i-1] + delta
        angle[i] %= 360

    return temperature, speed, light, angle

# ========================
# 2. SQL文件生成
# ========================
def generate_sql(data, filename):
    """将数据写入SQL文件"""
    with open(filename, 'w') as f:
        # 创建PLC数据表
        f.write("-- PLC Data Table\n")
        f.write("CREATE TABLE IF NOT EXISTS sensor_data (\n")
        f.write("    plc_id INT,\n")
        f.write("    time DATETIME,\n")
        f.write("    temperature FLOAT,\n")
        f.write("    speed FLOAT,\n")
        f.write("    light FLOAT,\n")
        f.write("    angle FLOAT,\n")
        f.write("    PRIMARY KEY (plc_id, time),\n")
        f.write("    FOREIGN KEY (plc_id) REFERENCES plc_list(plc_id)\n")
        f.write(");\n\n")
        
        # 插入传感器数据
        f.write("\n-- Sensor Data\n")
        plc_id = 1  # 假设只插入第一个PLC的数据
        batch_size = 500
        for i in range(0, len(data['time']), batch_size):
            batch = data['time'][i:i+batch_size]
            f.write("INSERT INTO sensor_data (plc_id, time, temperature, speed, light, angle) VALUES\n")
            rows = []
            for j in range(len(batch)):
                t = batch[j].strftime("%Y-%m-%d %H:%M:%S")
                rows.append(
                    f"({plc_id}, '{t}', {data['temp'][i+j]:.15f}, "
                    f"{data['speed'][i+j]:.15f}, {data['light'][i+j]:.15f}, "
                    f"{data['angle'][i+j]:.15f})"
                )
            f.write(",\n".join(rows) + ";\n")

# ========================
# 3. 可视化函数
# ========================
def plot_sensor_data(data):
    """绘制四类传感器的时序图"""
    fig, axs = plt.subplots(4, 1, figsize=(14, 12))
    plt.suptitle('Wind Turbine Sensor Data Simulation', y=0.95)

    # 温度图
    axs[0].plot(data['time'], data['temp'], color='#FF6B6B')
    axs[0].set_ylabel('Temperature (°C)', fontsize=10)
    axs[0].grid(alpha=0.3)

    # 转速图
    axs[1].plot(data['time'], data['speed'], color='#4ECDC4')
    axs[1].set_ylabel('Rotor Speed (m/s)', fontsize=10)
    axs[1].grid(alpha=0.3)

    # 光照图
    axs[2].plot(data['time'], data['light'], color='#FFD700')
    axs[2].set_ylabel('Light Intensity (lux)', fontsize=10)
    axs[2].grid(alpha=0.3)

    # 角度图
    axs[3].plot(data['time'], data['angle'], color='#6B486B')
    axs[3].set_ylabel('Yaw Angle (°)', fontsize=10)
    axs[3].grid(alpha=0.3)

    # 设置时间轴格式
    date_fmt = mdates.DateFormatter('%m-%d')
    for ax in axs:
        ax.xaxis.set_major_formatter(date_fmt)
        ax.xaxis.set_major_locator(mdates.DayLocator(interval=3))
    
    plt.tight_layout()
    plt.show()

# ========================
# 主程序
# ========================
if __name__ == "__main__":
    # 生成时间序列（2025-03-01到2025-03-31，每15分钟）
    time_points = []
    current = dt(2025, 3, 1)
    end = dt(2025, 4, 1) - timedelta(minutes=15)
    while current <= end:
        time_points.append(current)
        current += timedelta(minutes=15)

    # 生成传感器数据
    temp, speed, light, angle = generate_sensor_data(time_points, random_seed)
    
    # 组装数据字典
    data = {
        'time': time_points,
        'temp': temp,
        'speed': speed,
        'light': light,
        'angle': angle
    }
    
    # 生成SQL文件
    generate_sql(data, 'sensor_data.sql')
    print("SQL文件已生成：sensor_data.sql")
    
    # 可视化数据
    plot_sensor_data(data)