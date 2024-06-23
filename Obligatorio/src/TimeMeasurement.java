public class TimeMeasurement {
    public static long measureExecutionTime(Runnable process) {
        long startTime = System.currentTimeMillis();
        process.run();
        long endTime = System.currentTimeMillis();
        return  endTime - startTime;
    }
}
