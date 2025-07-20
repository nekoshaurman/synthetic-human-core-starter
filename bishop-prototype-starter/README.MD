## start app

docker-compose up

mvn spring-boot:run

POST example:
http://localhost:8080/commands
{
"description": "Test message",
"priority": "COMMON",
"author": "Ash",
"time": "2025-07-16T18:00:00Z"
}