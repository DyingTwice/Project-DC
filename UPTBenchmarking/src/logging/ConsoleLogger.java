package logging;


import java.util.concurrent.TimeUnit;

public class ConsoleLogger implements ILog {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(long integer) {
        System.out.println(integer);
    }

    @Override
    public void write(long value, TimeUnit unit) {
        System.out.println(convertToUnit(value, unit) + " " + unit );
    }

    @Override
    public void write(String string, long value, TimeUnit unit) {
        System.out.println(string + " " + convertToUnit(value, unit) + " " + unit);
    }

    @Override
    public void close() {

    }

    private long convertToUnit(long value, TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS:
                return value;
            case MICROSECONDS:
                return value / 1_000;
            case MILLISECONDS:
                return value / 1_000_000;
            case SECONDS:
                return value / 1_000_000_000;
            default:
                throw new IllegalArgumentException("Unsupported time unit: " + unit);
        }
    }

}
