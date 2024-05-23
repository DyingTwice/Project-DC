// TimeUnit.java
package logging;

public enum TimeUnit {
    NANO(1),
    MICRO(1_000),
    MILLI(1_000_000),
    SEC(1_000_000_000);

    private final long multiplier;

    TimeUnit(long multiplier) {
        this.multiplier = multiplier;
    }

    public long convert(long time, TimeUnit targetUnit) {
        return time * targetUnit.multiplier / this.multiplier;
    }
}

