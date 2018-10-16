package game;

import game.draw.Drawer;
import game.entities.Entity;
import game.entities.Player;
import game.logic.lists.SimpleList;
import javafx.scene.layout.Pane;
import web.service.wave.WaveGenerator;

/**
 * Singleton class that manages the Game cycle
 * @author José Acuña
 */
public class GameController <T> extends Thread{

    private static GameController instance;
    private Thread thread;
    private SimpleList<Entity> entities;
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
            instance.thread = instance;
        }
        return instance;
    }

    public void start(Pane pane){
        Drawer.init(pane);
        Player.getInstance().generatePlayer();
        thread.resume();

    }

    public void abort(){
        running = false;
    }

    public void run(){
        getWave();
        while (running){

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
        Drawer.getInstance().draw();
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
