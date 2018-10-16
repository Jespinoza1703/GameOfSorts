package game;

import game.draw.Drawer;
import game.entities.Dragon;
import game.entities.Player;
import game.logic.lists.SimpleList;
import web.service.wave.WaveGenerator;

/**
 * Singleton class that manages the Game cycle
 * @author José Acuña
 */
public class GameController extends Thread{

    private static GameController instance;
    private Thread thread;
    private Player player;
    private SimpleList<Dragon> dragons = new SimpleList<>();
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
        player.update();
        for (int i = 0; i < dragons.getLarge(); i++){
            dragons.getByIndex(i).getValue().update();
        }
    }

    private void draw(){
        Drawer.getInstance().draw();
    }

    private void end(){
        thread.stop();
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    public void addEntity(Dragon dragon){
        dragons.addAtEnd(dragon);
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
