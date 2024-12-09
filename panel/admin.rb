require 'socket'
require 'google/protobuf'
require_relative 'capacity_pb'
require_relative 'message_pb'

class Admin
  SERVERS = [
    { name: "Server1", host: "localhost", port: 5001 },
    { name: "Server2", host: "localhost", port: 5002 },
    { name: "Server3", host: "localhost", port: 5003 }
  ]

  def initialize
    @running = true
  end

  def start
    SERVERS.each do |server|
      Thread.new { monitor_server(server) }
    end

    while @running
      sleep(1)
    end
  end

  def monitor_server(server)
    loop do
      begin
        socket = TCPSocket.open(server[:host], server[:port])

        demand_message = Message::DemandMessage.new(demand: "CPCTY", response: "")
        socket.write(demand_message.to_proto)

        response_data = socket.read
        unless response_data.nil? || response_data.empty?
          capacity_response = Capacity.decode(response_data)
          puts "#{server[:name]} Capacity: #{capacity_response.server_status}"
          puts "Timestamp: #{capacity_response.timestamp}"

          send_to_plotter(server[:host], server[:port], capacity_response)
          
          if capacity_response.server_status.to_i > 0
            demand_message.response = "YEP"
            socket.write(demand_message.to_proto)
          end
        else
          puts "No response from #{server[:name]}"
        end
        socket.close
      rescue StandardError => e
        puts "Error communicating with #{server[:name]}: #{e.message}"
      end

      sleep(5) 
    end
  end

  def send_to_plotter(host, port, capacity_response)
    begin
      socket = TCPSocket.new('localhost', 5004)
      plot_data = {
        'host' => host,
        'port' => port,
        'server_status' => capacity_response.server_status,
        'timestamp' => capacity_response.timestamp
      }
      socket.puts plot_data.to_json 
      socket.close
    rescue StandardError => e
      puts "Error sending data to plotter: #{e.message}"
    end
  end
end

Admin.new.start
