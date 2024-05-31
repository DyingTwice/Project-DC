package testbench;

//import bench.IBenchmark;
import bench.cpu.CPUDigitsOfPi;
import logging.ConsoleLogger;
import logging.ILog;
import timing.ITimer;
import timing.Timer;

import java.util.concurrent.TimeUnit;

public class TestCPUDigitsOfPi {
    public static void main(String[] args) {
        CPUDigitsOfPi benchmark = new CPUDigitsOfPi();

        ITimer timer = new Timer();

        ILog logger = new ConsoleLogger();

        long ElapsedTime1;
        long ElapsedTime2;

        benchmark.initialize(100);
        timer.resume();
        benchmark.run("chudnovsky");
        ElapsedTime1 = timer.pause();
        logger.write("Chudnovsky algorithm took " + ElapsedTime1  + " " +TimeUnit.MICROSECONDS);
        timer.resume();
        benchmark.run("bbp");
        ElapsedTime2 = timer.pause();
        logger.write("BBP algorithm took " + ElapsedTime2  +" " + TimeUnit.MICROSECONDS);

        timer.stop();

        logger.close();

        benchmark.clean();
    }
}
