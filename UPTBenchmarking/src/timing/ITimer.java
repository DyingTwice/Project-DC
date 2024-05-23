package timing;

public interface ITimer {
    // Saves the current elapsed time and resets any previously stored total time
    void start();

    // Returns the elapsed time since the start of the timer
    // Make sure to return differences between stored times, not the absolute current time
    // Stop should return the total (cumulated) elapsed time since start was called
    long stop();

    // Saves the current elapsed time without resetting any previously saved times
    void resume();

    // Returns the elapsed time (difference) since the last start or resume of the timer
    long pause();
}

