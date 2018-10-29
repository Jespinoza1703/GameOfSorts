package util;

/**
 * Singleton class to manage time relating's
 * @author José Acuña
 */
public class Clock {

    private static Clock instance;
    private java.time.Clock clock = java.time.Clock.systemUTC();

    public static Clock getInstance() {
        if (instance == null){
            instance = new Clock();
        }
        return instance;
    }

    private Clock() {

    }

    /**
     * Creates a delay before next action takes place
     * @param FPS speed of the delay given in FPS/second
     */
    public void ticks(int FPS) {
        try {
            int millis = 1000 / FPS;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getTime(){
        return clock.millis();
    }
}
