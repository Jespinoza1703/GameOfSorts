package game;

/**
 * Singleton class that manages the Game cycle
 * @author José Acuña
 */
public class GameController {

    private static GameController instance;
    private boolean paused;
    private boolean state;
    private int wave;

    private GameController(){
        paused = false;
        state = false;
        wave = 0;
    }

    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }

    public void start(){

    }

    private void getWave(){

    }

    private void event(){

    }

    private void pause(){

    }

    private void action(){

    }

    private void draw(){

    }

    public boolean isWaveClear(){
        return wave == 0;
    }

    public boolean isPaused(){
        return paused;
    }

    private boolean isGameFinished(){
        return state;
    }
}
