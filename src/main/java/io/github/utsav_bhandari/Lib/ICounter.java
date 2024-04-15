package io.github.utsav_bhandari.Lib;

public interface ICounter {
    int increment();

    boolean isMax();
    boolean isMin();

    int setValue(int value);
    int getValue();
    void reset();
}
