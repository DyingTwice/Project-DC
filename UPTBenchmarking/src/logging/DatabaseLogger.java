package logging;

import java.util.concurrent.TimeUnit;

public abstract class DatabaseLogger implements ILog {
    // Implement DatabaseLogger according to your database setup
    // You can use JDBC or any other database connectivity method here

    @Override
    public void write(String message) {
        // Write to database
    }


    public void writeTime(long value, TimeUnit unit) {
        // Convert value to the appropriate unit and write to database
        long convertedValue = convertToUnit(value, unit);
        // Write convertedValue to database
    }


    public void writeTime(String string, long value, TimeUnit unit) {
        // Convert value to the appropriate unit and write to database
        long convertedValue = convertToUnit(value, unit);
        // Write string and convertedValue to database
    }

    @Override
    public void close() {
        // Close database connection
    }

    // Helper method to convert time value to the specified unit
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
