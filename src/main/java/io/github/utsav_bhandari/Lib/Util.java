package io.github.utsav_bhandari.Lib;

import java.util.HashMap;
import java.util.Set;

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
}
