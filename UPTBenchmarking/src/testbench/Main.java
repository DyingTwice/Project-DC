package testbench;

import bench.BubbleSortBenchmark;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.DatabaseLogger;
import logging.FileLogger;
import logging.ILog;
import timing.ITimer;
import timing.Timer;
import logging.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Example usage of BubbleSortBenchmark
        IBenchmark benchmark = new BubbleSortBenchmark(); // Use interface type for flexibility
        ITimer timer = new Timer(); // Use interface type for flexibility
        ILog logger = new ConsoleLogger(); // Use interface type for flexibility
        //ILog logger2 = new FileLogger();
       // ILog logger3 = new ConsoleLogger();
        final int workload = 100;

        benchmark.initialize(workload);

        for (int i = 0; i < 12; ++i) {
            timer.resume();
            benchmark.run();
            long time = timer.pause();
            logger.writeTime("Run " + i + ":", time, TimeUnit.NANO);
        }
        logger.writeTime("Finished in", timer.stop(), TimeUnit.NANO);
        // Initialize benchmark with array size
        benchmark.initialize(1000); // Initialize array with size 1000

        // Start the timer before running the benchmark
        timer.start();

        // Run benchmark
        benchmark.run();

        // Stop the timer after the benchmark completes
        long elapsedTime = timer.stop();
        logger.writeTime("Total elapsed time:", elapsedTime, TimeUnit.NANO);

        logger.close();

        // Clean up after benchmark
        benchmark.clean();
    }
}
