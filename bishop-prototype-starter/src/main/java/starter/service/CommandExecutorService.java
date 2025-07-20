package starter.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;

import starter.annotations.WeylandWatchingYou;
import starter.dto.CommandRequest;
import starter.exceptions.QueueOverflowException;
import starter.model.CommandPriority;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.RejectedExecutionException;

@Service
public class CommandExecutorService {

    private final ThreadPoolExecutor executor;
    private final MeterRegistry meterRegistry;

    private final Map<String, Counter> authorCommandCounters = new ConcurrentHashMap<>();

    private final Counter totalCommandsCounter;
    private final Counter criticalCommandsCounter;
    private final Counter commonCommandsCounter;

    public CommandExecutorService(ThreadPoolExecutor executor, MeterRegistry meterRegistry) {
        this.executor = executor;
        this.meterRegistry = meterRegistry;

        // size of queue
        Gauge.builder("android.command.queue.size", executor.getQueue(), q -> (double) q.size())
                .description("Текущий размер очереди команд")
                .register(meterRegistry);

        // count of commands
        this.totalCommandsCounter = meterRegistry.counter("android.commands.executed.total");

        //count of common and critical commands
        this.criticalCommandsCounter = meterRegistry.counter("android.commands.executed", "priority", "CRITICAL");
        this.commonCommandsCounter = meterRegistry.counter("android.commands.executed", "priority", "COMMON");
    }

    @WeylandWatchingYou
    public void execute(CommandRequest command) {
        try {
            if (command.getPriority() == CommandPriority.CRITICAL) {
                // немедленно выполнять критические задачи
                executeImmediately(command);
            } else {
                // добавить задачу в очередь
                executor.execute(() -> executeCommand(command));
            }
        } catch (RejectedExecutionException ex) {
            throw new QueueOverflowException("Очередь задач переполнена");
        }
    }

    private void executeImmediately(CommandRequest command) {
        totalCommandsCounter.increment();
        criticalCommandsCounter.increment();
        incrementAuthorCounter(command.getAuthor());

        System.out.println("Выполнение критической команды: " + command.getDescription());
    }

    private void executeCommand(CommandRequest command) {
        totalCommandsCounter.increment();
        commonCommandsCounter.increment();
        incrementAuthorCounter(command.getAuthor());

        System.out.println("Выполнение обычной команды: " + command.getDescription());
    }

    private void incrementAuthorCounter(String author) {
        authorCommandCounters.computeIfAbsent(author, a ->
                meterRegistry.counter("android.commands.executed.by_author", "author", a)
        ).increment();
    }

    public Integer getQueueSize() {
        return executor.getQueue().size();
    }
}
