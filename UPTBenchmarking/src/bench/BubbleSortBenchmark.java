package bench;

public class BubbleSortBenchmark implements IBenchmark {
    private int[] arrayToSort;
    private boolean running;

    @Override
    public void run() {
        if (arrayToSort == null) {
            System.err.println("Array not initialized. Call initialize() first.");
            return;
        }

        long startTime = System.nanoTime();

        // Core benchmarking code: Bubble Sort
        bubbleSort(arrayToSort);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Bubble Sort benchmark completed in " + elapsedTime + " nanoseconds.");

        // Compute and print the normalized offset
        long expectedTime = arrayToSort.length * 1_000_000L; // Convert milliseconds to nanoseconds
        double offset = 100.0 * (elapsedTime - expectedTime) / expectedTime;
        System.out.println("Normalized offset: " + offset + "%");

        // Check if the benchmark should be cancelled
        if (!running) {
            System.out.println("Benchmark cancelled.");
            //return;
        }

        //clean(); // Cleaning up after benchmark
    }

    @Override
    public void run(int[] arrayToSort) {
        if (arrayToSort == null) {
            System.err.println("Array not initialized. Call initialize() first.");
            return;
        }
        this.arrayToSort = arrayToSort;
        run();
    }

    @Override
    public void initialize(int size) {
        arrayToSort = generateRandomArray(size);
        System.out.println("Array initialized with size " + size);
    }

    @Override
    public void clean() {
        arrayToSort = null;
    }

    @Override
    public void cancel() {
        running = false;
    }

    // Utility method to generate a random integer array
    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100); // Generate random numbers between 0 and 99
        }
        return array;
    }

    // Bubble Sort algorithm
    private void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
