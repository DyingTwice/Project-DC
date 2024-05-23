package logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class FileLogger implements ILog {
    private static final Logger LOGGER = Logger.getLogger(FileLogger.class.getName());
    private PrintWriter writer;

    public FileLogger(String fileName) {
        try {
            writer = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while initializing FileLogger", e);
        }
    }

    @Override
    public void write(String message) {
        writer.println(message);
        writer.flush();
    }


    public void writeTime(long value, TimeUnit unit) {
        writer.println(convertToString(value, unit));
        writer.flush();
    }


    public void writeTime(String string, long value, TimeUnit unit) {
        writer.println(string + ": " + convertToString(value, unit));
        writer.flush();
    }

    @Override
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }

    // Helper method to convert time value to string with appropriate unit
    private String convertToString(long value, TimeUnit unit) {
        long convertedValue;
        String unitName;
        switch (unit) {
            case NANOSECONDS:
                convertedValue = value;
                unitName = "ns";
                break;
            case MICROSECONDS:
                convertedValue = value / 1_000;
                unitName = "us";
                break;
            case MILLISECONDS:
                convertedValue = value / 1_000_000;
                unitName = "ms";
                break;
            case SECONDS:
                convertedValue = value / 1_000_000_000;
                unitName = "sec";
                break;
            default:
                throw new IllegalArgumentException("Unsupported time unit: " + unit);
        }
        return convertedValue + " " + unitName;
    }
}
