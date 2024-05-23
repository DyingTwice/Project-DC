package timing;

public class Timer implements ITimer {
    private long startTime; // Stores the start time
    private long totalTime; // Stores the total elapsed time

    @Override
    public void start() {
        startTime = System.nanoTime(); // Save the current elapsed time in nanoseconds
        totalTime = 0; // Reset any previously stored total time
    }

    @Override
    public long stop() {
        long currentTime = System.nanoTime(); // Get the current elapsed time in nanoseconds
        long elapsedTime = currentTime - startTime; // Calculate the elapsed time since the start
        totalTime += elapsedTime; // Add the elapsed time to the total elapsed time
        return totalTime; // Return the total (cumulated) elapsed time since start was called
    }

    @Override
    public void resume() {
        // Save the current elapsed time without resetting any previous saved times
        startTime = System.nanoTime();
    }

    @Override
    public long pause() {
        long currentTime = System.nanoTime(); // Get the current elapsed time in nanoseconds
        long elapsedTime = currentTime - startTime; // Calculate the elapsed time since the last start or resume
        return elapsedTime; // Return the elapsed time (difference)
    }
}
