package io.github.utsav_bhandari.Lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {
    public static void unsafeWait(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Runnable, Thread> threadPool;

    /**
     * Prevents this runnable from being called if this runnable is already running
     * <br>
     * DO NOT PASS IN UNNAMED RUNNABLES. THEY ARE DIFFERENT TO THE JVM EACH TIME
     */
    public static void runSyncThread(Runnable r) {
        if (threadPool == null) {
            threadPool = new HashMap<>();
        }

        if (threadPool.containsKey(r)) {
            return;
        }

        Thread t = new Thread(() -> {
            try {
                r.run();
            } finally {
                threadPool.remove(r);
            }
        });

        threadPool.put(r, t);

        t.start();
    }

    public static <T> ArrayList<T> cloneArray(List<T> list) {
        return new ArrayList<>(list);
    }

    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }
}
