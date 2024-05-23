// ILogger.java
package logging;

public interface ILog {
    void write(String message);

    void writeTime(long value, TimeUnit unit);

    void writeTime(String string, long value, TimeUnit unit);

    void close();
}
