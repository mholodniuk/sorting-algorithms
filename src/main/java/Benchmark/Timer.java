package Benchmark;

public class Timer {
    public static enum Precision {
        MILLISECONDS,
        NANOSECONDS
    }
    private long start;
    private final Precision precision;

    public Timer(Precision precision) {
        this.precision = precision;
        this.start = 0;
    }

    public void start() {
        this.start = 0;
        if(precision == Precision.MILLISECONDS)
            this.start = System.currentTimeMillis();
        if(precision == Precision.NANOSECONDS)
            this.start = System.nanoTime();
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