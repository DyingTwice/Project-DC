package testbench;

import bench.BubbleSortBenchmark;
import bench.IBenchmark;
import logging.ConsoleLogger;
//import logging.DatabaseLogger;
//import logging.FileLogger;
import logging.ILog;
import timing.ITimer;
import timing.Timer;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        // Example usage of BubbleSortBenchmark
        IBenchmark benchmark = new BubbleSortBenchmark(); // Use interface type for flexibility
        ITimer timer = new Timer(); // Use interface type for flexibility // Use interface type for flexibility
        ILog logger = new ConsoleLogger(); // Use interface type for flexibility
        final int workload = 1000;

        benchmark.initialize(workload);
        long totalElapsedTime = 0;

        for (int i = 0; i < 12; ++i) {
            timer.resume();
            benchmark.run();
            long time = timer.pause();
            totalElapsedTime += time;
            logger.write("Run " + i + ":", time, TimeUnit.MICROSECONDS);
        }
        logger.write("Finished in", totalElapsedTime, TimeUnit.MICROSECONDS);
        timer.stop();

        logger.close();

        // Clean up after benchmark
        benchmark.clean();

    }
}
