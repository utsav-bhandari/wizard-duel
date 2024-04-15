package io.github.utsav_bhandari.Lib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZigzagCounterTest {
    ZigzagCounter zc;

    @BeforeEach
    void setUp() {
        zc = new ZigzagCounter(0, 5, 1);
    }

    @Test
    void increment() {
        assertEquals(zc.getValue(), 1);
        zc.increment();
        assertEquals(zc.getValue(), 2);
        zc.increment();
        assertEquals(zc.getValue(), 3);
        zc.increment();
        assertEquals(zc.getValue(), 4);
        zc.increment();
        assertEquals(zc.getValue(), 5);
        zc.increment();
        assertEquals(zc.getValue(), 4);
        zc.increment();
        assertEquals(zc.getValue(), 3);
        zc.increment();
        assertEquals(zc.getValue(), 2);
        zc.increment();
        assertEquals(zc.getValue(), 1);
        zc.increment();
        assertEquals(zc.getValue(), 0);
        zc.increment();
        assertEquals(zc.getValue(), 1);
        zc.increment();
        assertEquals(zc.getValue(), 2);
    }

    // For min and max
    // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19
    // 0  1  2  3  4  5  4  3  2  1  0  1  2  3  4  5  4  3  2  1

    @Test
    void isMin() {
        zc.setValue(0);
        for (int i = 0; i < 20; i++) {
            if (i == 0 || i == 10) {
                assertTrue(zc.isMin(), "i = " + i + " value = " + zc.getValue());
            } else {
                assertFalse(zc.isMin(), "i = " + i + " value = " + zc.getValue());
            }

            zc.increment();
        }
    }

    @Test
    void isMax() {
        zc.setValue(0);

        for (int i = 0; i < 20; i++) {
            if (i == 5 || i == 15) {
                assertTrue(zc.isMax(), "i = " + i + " value = " + zc.getValue());
            } else {
                assertFalse(zc.isMax(), "i = " + i + " value = " + zc.getValue());
            }

            zc.increment();
        }
    }

    @Test
    void setValue() {
        zc.setValue(3);
        assertEquals(zc.getValue(), 3);

        zc.setValue(6);
        assertEquals(zc.getValue(), 4);

        zc.setValue(7);
        assertEquals(zc.getValue(), 3);

        zc.setValue(8);
        assertEquals(zc.getValue(), 2);

        zc.setValue(9);
        assertEquals(zc.getValue(), 1);

        zc.setValue(10);
        assertEquals(zc.getValue(), 0);

        zc.setValue(11);
        assertEquals(zc.getValue(), 1);
    }
}