package game;

import client.Wave;
import game.draw.Drawer;
import game.entities.Background;
import game.entities.Entity;
import game.entities.Player;
import game.event.handler.Collisions;
import game.logic.lists.SimpleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import util.Clock;

/**
 * Singleton class that manages the Game cycle
 * @author José Acuña
 */
public class GameController extends Thread{

    private static Logger logger = LoggerFactory.getLogger(GameController.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private static final Marker SPRITES = MarkerFactory.getMarker("SPRITES");
    private static final Marker WS = MarkerFactory.getMarker("WS");
    private static GameController instance;
    private Collisions collision = Collisions.getInstance();
    private Clock clock = Clock.getInstance();
    private Thread thread;
    private SimpleList<Entity> entities = new SimpleList<>();
    private Wave wave = new Wave();
    public static Player player;
    private boolean paused;
    private boolean running;

    private GameController(String msg){
        super(msg);
        paused = false;
        running = true;
    }

    public static GameController getInstance(){
        if (instance == null){
            instance = new GameController("game");
            player = new Player();
            instance.thread = instance;
            instance.thread.start();
            logger.info(SYS, "Game thread started as: " + instance.thread.getName());
        }
        return instance;
    }

    public void abort(){
        running = false;
        logger.info(SYS, "Game aborted");
    }

    @Override
    public void run(){
        loadBackGround();
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

    private void loadBackGround() {
        logger.info(SPRITES, "Loading parallax backgrounds...");
        new Background(1, 0, Drawer.height, Drawer.width, Drawer.height,"file:res/img/backgrounds/parallax1/layer-1-sky.png", 1);
        new Background(2, 0, Drawer.height, Drawer.width, Drawer.height / 1.2,"file:res/img/backgrounds/parallax1/layer-2-mountain.png", 2);
        }

    private void getWave(){
        logger.info(WS, "Game ask for wave");
    }

    private void event(){

    }

    private void pause(){
        logger.info(SYS, "Game Paused");
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

    public void deleteEntity(Entity entity){
        logger.info(SPRITES, "Removing from the game: " + entity.toString());
        entities.delete(entities.searchIndex(entity));
    }

    private void end(){
        logger.info(SYS, "Game thread stop: " + thread.getName());
        instance = null;
        thread.stop();
    }

    public void addEntity(Entity entity){
        logger.info(SPRITES, "Adding to the game: " + entity.toString());
        entities.addAtEnd(entity);
    }

    private void verifyCollisions(){
        if (player.isAlive()){
            Boolean collision_player_with_dragon = collision.collide(player, collision.getDragons(), true);
            Boolean collision_player_with_dragonBullet = collision.collide(player, collision.getDragonBullets(), true);
            if(collision_player_with_dragon || collision_player_with_dragonBullet){
                player.hit();
            }
        }
        collision.collide(collision.getDragons(), collision.getPlayerBullets(), true, true);
        collision.collide(collision.getPlayerBullets(), collision.getDragonBullets(), true, true);

    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }

    public boolean isWaveClear(){
        return wave.getSize() == 0;
    }

    public boolean isPaused(){
        return paused;
    }

    public boolean isGameRunning(){
        return running;
    }
}
