package io.github.utsav_bhandari.Lib;

public class ZigzagCounter implements ICounter {
    private final int min;
    private final int max;
    private int value;
    private boolean incrementing = true;
    private final int defaultValue;

    public ZigzagCounter(int min, int max, int defaultValue) {
        if (min >= max) {
            throw new IllegalArgumentException("min must be less than max");
        }

        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;

        if (defaultValue < min || defaultValue > max) {
            throw new IllegalArgumentException("defaultValue must be in range [min, max]");
        }

        this.value = defaultValue;
    }

    public int increment() {
        value += incrementing ? 1 : -1;
        // Big brain
        incrementing = (value == min || value == max) != incrementing;

        return value;
    }

    public boolean isMin() {
        return value == min;
    }

    public boolean isMax() {
        return value == max;
    }

    public int getValue() {
        return value;
    }

    public void reset() {
        value = defaultValue;
        incrementing = true;
    }

    protected int getZigzagMod(int value) {
        int d2 = (max - min) * 2;

        int v = (value - min) % d2 + min;

        if (v < min) {
            v += d2;
        }

        incrementing = v < max;

        // if v is greater than max, fold it the other way
        if (v >= max) {
            v = d2 - v;
        }

        return v;
    }

    public int setValue(int value) {
        return this.value = getZigzagMod(value);
    }
}
