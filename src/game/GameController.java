package game;

import game.draw.Drawer;
import game.entities.Entity;
import game.entities.Player;
import game.logic.lists.SimpleList;
import util.Clock;
import web.service.wave.WaveGenerator;

/**
 * Singleton class that manages the Game cycle
 * @author José Acuña
 */
public class GameController extends Thread{

    private static GameController instance;
    private Clock clock = Clock.getInstance();
    private Thread thread;
    private SimpleList<Entity> entities = new SimpleList<>();
    private boolean paused;
    private boolean running;
    private int wave;

    private GameController(String msg){
        super(msg);
        paused = false;
        running = true;
        wave = 0;
    }

    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController("game");
            Player.getInstance().generatePlayer();
            instance.thread = instance;
            instance.thread.start();
        }
        return instance;
    }

    public void abort(){
        running = false;
    }

    @Override
    public void run(){
        getWave();
        while (running){
            clock.ticks(60);

            if(isWaveClear()){
                getWave();
            }

            event();

            if(isPaused()){
                pause();
            }

            update();
            draw();

            if(!isGameRunning()){
                running = false;
            }
        }
        end();
    }

    private void getWave(){
        WaveGenerator.generateWave();
    }

    private void event(){

    }

    private void pause(){
        while (isPaused()){
            event();
            draw();
        }
    }

    private void update(){
        for (int i = 0; i < entities.getLarge(); i++){
            entities.getByIndex(i).getValue().update();
        }
    }

    private void draw(){
        //Drawer.getInstance().draw();
    }

    private void end(){
        thread.stop();
    }

    public void addEntity(Entity entity){
        entities.addAtEnd(entity);
    }

    public boolean isWaveClear(){
        return wave == 0;
    }

    public boolean isPaused(){
        return paused;
    }

    public boolean isGameRunning(){
        return running;
    }
}
