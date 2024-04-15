package io.github.utsav_bhandari.Lib;

public class FrameMixer {
    private final float sourceDt;
    private final float destDt;
    private float time = 0;

    public FrameMixer(float sourceFps, float destFps) {
        this.sourceDt = 1000 / sourceFps;
        this.destDt = 1000 / destFps;
    }

    /**
     * Return number of frame counts
     */
    public int tick() {
        time += sourceDt;
        int count = 0;
        while (time >= destDt) {
            time -= destDt;
            count++;
        }
        return count;
    }
}
