package io.github.utsav_bhandari.Lib;

public class IntStatistics {
    private int sumPositiveDelta = 0;
    private int sumNegativeDelta = 0;

    public IntStatistics() {
    }

    public void add(int delta) {
        sumPositiveDelta += delta;
    }

    public void subtract(int delta) {
        sumNegativeDelta -= delta;
    }

    public int getSumPositiveDelta() {
        return sumPositiveDelta;
    }

    public int getSumNegativeDelta() {
        return sumNegativeDelta;
    }

    public void reset() {
        sumPositiveDelta = 0;
        sumNegativeDelta = 0;
    }
}
