package logging;

import java.util.concurrent.TimeUnit;

public class ConsoleLogger implements ILog {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void writeTime(long value, logging.TimeUnit unit) {
        System.out.println(value + " " + unit );
    }

    @Override
    public void writeTime(String string, long value, logging.TimeUnit unit) {
        System.out.println(string + " " + value + " " + unit);
    }

    @Override
    public void close() {
        // No resources to close for ConsoleLogger
    }
}
