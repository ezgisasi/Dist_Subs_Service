import socket
import matplotlib.pyplot as plt
from collections import deque
import threading
from capacity_pb2 import Capacity
import numpy as np  

PLOTTER_PORT = 5005
ADMIN_PORT = 5004  

capacity_data = {1: deque(maxlen=100), 2: deque(maxlen=100), 3: deque(maxlen=100)}

data_lock = threading.Lock()

def receive_capacity_data(connection):
    while True:
        received_data = connection.recv(1024)
        if not received_data:
            break
        capacity = Capacity()
        capacity.ParseFromString(received_data)

        with data_lock:
            capacity_data[capacity.server_id].append(capacity.value)

def plot_capacity_data():
    plt.ion()
    fig, ax = plt.subplots()
    
    colors = {1: 'cyan', 2: 'magenta', 3: 'yellow'}
    markers = {1: 'o', 2: 's', 3: '^'} 
    linestyles = {1: '-', 2: '--', 3: '-.'}
    
    lines = {server_id: ax.plot([], [], label=f"Sunucu {server_id}", color=color, marker=marker, linestyle=linestyle)[0]
             for server_id, (color, marker, linestyle) in enumerate(zip(colors.values(), markers.values(), linestyles.values()), 1)}

    ax.legend()
    ax.set_xlabel("Zaman (s)")
    ax.set_ylabel("Kapasite (%)")
    ax.set_title("Sunucu Kapasitesi Zamanla")

    while True:
        with data_lock:
            for server_id, line in lines.items():
                data = list(capacity_data[server_id])
                line.set_data(np.arange(len(data)), data) 
            ax.relim()
            ax.autoscale_view()
        plt.pause(0.5) 

def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
        server.bind(('localhost', ADMIN_PORT))
        server.listen(3)

        def accept_clients():
            while True:
                connection, _ = server.accept()
                threading.Thread(target=receive_capacity_data, args=(connection,), daemon=True).start()

        threading.Thread(target=accept_clients, daemon=True).start()

        plot_capacity_data()

if __name__ == "__main__":
    main()
