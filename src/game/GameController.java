package game;

import game.entities.Dragon;
import game.entities.Entity;
import game.entities.Player;
import game.event.handler.inputs.Collisions;
import game.logic.lists.SimpleList;
import util.Clock;

/**
 * Singleton class that manages the Game cycle
 * @author José Acuña
 */
public class GameController extends Thread{

    private static GameController instance;
    private Collisions collision = Collisions.getInstance();
    private Clock clock = Clock.getInstance();
    private Thread thread;
    private SimpleList<Entity> entities = new SimpleList<>();
    private Player player;
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
            instance.player = new Player();
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
        }
        end();
    }

    private void getWave(){
        Dragon dragon = new Dragon(1000, 350);
        wave = 1;
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
        verifyCollisions();

    }

    private void draw(){

    }

    public void deleteEntity(Entity draw){
        entities.delete(entities.searchIndex(draw));
    }

    private void end(){
        instance = null;
        thread.stop();
    }

    public void addEntity(Entity entity){
        entities.addAtEnd(entity);
    }

    public boolean isWaveClear(){
        return wave == 0;
    }

    private void verifyCollisions(){
        Boolean collision_player_with_dragon = collision.collide(player, collision.getDragons(), true);
        Boolean collision_player_with_dragonBullet = collision.collide(player, collision.getDragonBullets(), true);
        collision.collide(collision.getDragons(), collision.getPlayerBullets(), true, true);
        collision.collide(collision.getPlayerBullets(), collision.getDragonBullets(), true, true);
        if(collision_player_with_dragon || collision_player_with_dragonBullet){
            player.hit();
        }
    }

    public boolean isPaused(){
        return paused;
    }

    public boolean isGameRunning(){
        return running;
    }
}
