// ILogger.java
package logging;


import java.util.concurrent.TimeUnit;
public interface ILog {
    void write(String message);

    void write(long integer);

    void write(long value, TimeUnit unit);

    void write(String string, long value, TimeUnit unit);

    void close();
}
