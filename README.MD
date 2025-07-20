## Разработка Synthetic Human Core Starter
После инцидента с андроидом на корабле «Ностромо» компанией было принято решение
создать единый synthetic-human-core-starter, который будет использоваться для
реализации логики работы всех будущих моделей искусственных людей.
Стартер должен содержать в себе следующее:
1. Модуль приема и исполнения команд;
2. Возможность мониторинга текущей занятости андроида;
3. Возможность аудита любых действий андроида;
4. Модуль обработки ошибок.

## Запуск эмулятора
Сборка всех модулей проекта через Maven
```bash
mvn clean install
```

Запуск кафки через докер с помощью docker-compose
```bash
docker-compose up
```

Запуск эмулятора
```bash
cd bishop-prototype-emulator
mvn spring-boot:run
```

## Запросы
Я использовал Postman для тестов, вот примеры запросов
### POST отправка команды на выполнение
```
http://localhost:8082/commands
{
"description": "Test message critical",
"priority": "CRITICAL",
"author": "Ben",
"time": "2025-08-16T19:00:00Z"
}
```

### GET состояния приложения
```
http://localhost:8082/actuator/health
```

### GET общее количество полученных запросов
```
http://localhost:8082/actuator/metrics/android.commands.executed.total
```

### GET все доступные метрики
```
http://localhost:8082/actuator/metrics
```
### GET текущий размер очереди
```
http://localhost:8082/actuator/metrics/android.command.queue.size
```

### GET количество запросов по автору
```
http://localhost:8082/actuator/metrics/android.commands.executed.by_author
```

## Стек
### SpringBoot, Maven, Kafka, Docker, Actuator + Micrometer