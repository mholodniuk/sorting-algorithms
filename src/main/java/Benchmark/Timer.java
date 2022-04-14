package Benchmark;

public class Timer {
    public enum Precision {
        MILLISECONDS,
        NANOSECONDS
    }
    private long start = 0;
    private final Precision precision;

    public Timer(Precision precision) {
        this.precision = precision;
    }

    public void start() {
        this.start = 0;
        if(precision == Precision.MILLISECONDS)
            start = System.currentTimeMillis();
        if(precision == Precision.NANOSECONDS)
            start = System.nanoTime();
    }

    public long stop() {
        long finish = 0;
        if(precision == Precision.MILLISECONDS)
            finish = System.currentTimeMillis();
        if(precision == Precision.NANOSECONDS)
            finish = System.nanoTime();
        return finish - start;
    }
}