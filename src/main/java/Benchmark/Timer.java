package Benchmark;

public class Timer {
    public enum Precision {
        MILLI,
        NANO
    }
    private long start = 0;
    private final Precision precision;

    public Timer(Precision precision) {
        this.precision = precision;
    }

    public void start() {
        if(precision == Precision.MILLI)
            start = System.currentTimeMillis();
        if(precision == Precision.NANO)
        start = System.nanoTime();
    }

    public long stop() {
        long finish = 0;
        if(precision == Precision.MILLI)
            finish = System.currentTimeMillis();
        if(precision == Precision.NANO)
            finish = System.nanoTime();
        return finish - start;
    }
}