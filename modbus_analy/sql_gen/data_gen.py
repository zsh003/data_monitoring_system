import random
import math
import pandas as pd
import matplotlib.pyplot as plt
from datetime import datetime

def generate_angle(previous_angle, max_step=5):
    step = random.uniform(-max_step, max_step)
    new_angle = previous_angle + step
    new_angle %= 360
    return new_angle

def generate_sensor_data(start_date, end_date, random_seed):
    random.seed(random_seed)
    times = pd.date_range(start=start_date, end=end_date, freq='15T')
    
    # 温度（昼夜温差）
    base_temp = 20
    temp_amplitude = 5
    temp_std = 1
    temps = []
    for time in times:
        hour = time.hour + time.minute/60
        temp = base_temp + temp_amplitude * (-math.cos(2 * math.pi * (hour/24)))
        temp += random.gauss(0, temp_std)
        temps.append(temp)
    
    # 速度（白天变化快，晚上变化慢）
    base_speed = 10
    speed_amplitude = 5
    speeds = []
    for time in times:
        hour = time.hour + time.minute/60
        speed = base_speed + speed_amplitude * math.cos(2 * math.pi * (hour/24 - 0.5))
        # 添加噪声
        if 6 <= hour < 18:
            noise_std = 3
        else:
            noise_std = 1
        speed += random.gauss(0, noise_std)
        speeds.append(speed)
    
    # 亮度（中午最大，凌晨最小）
    base_brightness = 1000
    brightness_std = 50
    brightnesses = []
    for time in times:
        hour = time.hour + time.minute/60
        brightness = base_brightness * (1 - math.cos(2 * math.pi * (hour/24))) / 2
        brightness += random.gauss(0, brightness_std)
        brightnesses.append(brightness)
    
    # 水平角度（随机游走）
    angles = []
    current_angle = random.uniform(0, 360)
    angles.append(current_angle)
    for _ in range(1, len(times)):
        current_angle = generate_angle(current_angle)
        angles.append(current_angle)
    
    df = pd.DataFrame({
        'time': times,
        'temperature': temps,
        'speed': speeds,
        'light': brightnesses,
        'angle': angles
    })
    
    return df

def generate_sql(df, plc_id=1):
    sql = []
    
    # 创建表 plc_1
    sql.append("CREATE TABLE sensor_data (")
    sql.append("    plc_id INT,")
    sql.append("    time DATETIME,")
    sql.append("    temperature FLOAT,")
    sql.append("    speed FLOAT,")
    sql.append("    light FLOAT,")
    sql.append("    angle FLOAT,")
    sql.append("    PRIMARY KEY (plc_id, time),")
    sql.append("    FOREIGN KEY (plc_id) REFERENCES plc_list(plc_id)")
    sql.append(");")
    
    # 插入 sensor_data 数据
    for _, row in df.iterrows():
        time_str = row['time'].strftime('%Y-%m-%d %H:%M:%S')
        sql.append(f"INSERT INTO sensor_data (plc_id, time, temperature, speed, light, angle) VALUES ({plc_id}, '{time_str}', {row['temperature']}, {row['speed']}, {row['light']}, {row['angle']});")
    
    return sql

def save_sql(sql_statements, filename):
    with open(filename, 'w') as f:
        for stmt in sql_statements:
            f.write(stmt + '\n')

def plot_data(df):
    fig, axes = plt.subplots(4, 1, figsize=(12, 12))
    
    df.plot(x='time', y='temperature', ax=axes[0], title='Temperature (°C)', color='red')
    axes[0].set_ylabel('°C')
    
    df.plot(x='time', y='speed', ax=axes[1], title='Speed (m/s)', color='blue')
    axes[1].set_ylabel('m/s')
    
    df.plot(x='time', y='light', ax=axes[2], title='Brightness (lux)', color='green')
    axes[2].set_ylabel('lux')
    
    df.plot(x='time', y='angle', ax=axes[3], title='Horizontal Angle (degrees)', color='purple')
    axes[3].set_ylabel('degrees')
    
    plt.tight_layout()
    plt.show()

def main():
    start_date = '2025-03-01'
    end_date = '2025-03-31'
    random_seed = int(input("Enter random seed (e.g., 42): "))
    
    df = generate_sensor_data(start_date, end_date, random_seed)
    plot_data(df)
    
    filename = f'sensor_data_seed_{random_seed}.sql'
    sql = generate_sql(df)
    save_sql(sql, filename)
    print(f"Data saved to {filename}")

if __name__ == '__main__':
    main()