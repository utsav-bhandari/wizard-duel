package io.github.utsav_bhandari.Lib;

public class CyclicCounter implements ICounter {
    private final int min;
    private final int max;
    private int value;

    private final int defaultValue;

    public CyclicCounter(int max) {
        this(0, max, 0);
    }

    public CyclicCounter(int min, int max, int defaultValue) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }

        this.min = min;
        this.max = max;

        if (defaultValue < min || defaultValue >= max) {
            throw new IllegalArgumentException("defaultValue must be in range [min, max). If you want to set defaultValue outside of this range, use setValue method.");
        }

        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    protected int getModValue(int value) {
        int d = max - min;
        int v = (value - min) % d + min;

        if (v < min) {
            v += d;
        }

        return v;
    }

    public int setValue(int value) {
        return this.value = getModValue(value);
    }

    /**
     * Efficient increment
     */
    public int increment() {
        if (isMax()) {
            value = min;
        } else {
            value++;
        }

        return value;
    }

    public int getValue() {
        return value;
    }

    public boolean isMin() {
        return value == min;
    }

    public boolean isMax() {
        return value == max - 1;
    }

    public void reset() {
        value = defaultValue;
    }
}
