docker run -d --name kafka -p 2181:2181 -p 9092:9092 -e ADVERTISED_HOST=localhost -e ADVERTISED_PORT=9092 spotify/kafka
