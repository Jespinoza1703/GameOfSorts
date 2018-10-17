package util;

import java.time.Instant;
import java.time.ZoneId;

/**
 * Singleton class to manage time relating's
 */
public class Clock {

    private static Clock instance;
    private java.time.Clock clock = java.time.Clock.systemUTC();;
    private long lastTime;

    public static Clock getInstance() {
        if (instance == null){
            instance = new Clock();
        }
        return instance;
    }

    private Clock() {
        lastTime = clock.millis();
    }

    public void ticks(int FPS) {
        try {
            int millis = 1000 / 60;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
