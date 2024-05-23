package bench;

public interface IBenchmark {
    // Method containing the core benchmarking code
    void run();

    // Method containing the core benchmarking code with parameters
    void run(int[] arrayToSort);

    // Method for initializing data needed for the benchmark
    void initialize(int size);

    // Method for cleaning up after the benchmark
    void clean();

    // Method for cancelling the benchmark execution
    void cancel();
}
