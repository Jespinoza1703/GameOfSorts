package game;

import client.Wave;
import client.WaveGenerator;
import game.draw.Drawer;
import game.entities.Background;
import game.entities.Dragon;
import game.entities.Entity;
import game.entities.Player;
import game.event.handler.Collisions;
import game.event.handler.inputs.KeyReader;
import game.logic.lists.SimpleList;
import graphics.controllers.sGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import util.Clock;

/**
 * Singleton class that manages the Game cycle
 *
 * @author José Acuña
 */
public class GameController extends Thread {

    public static Player player;
    private static Logger logger = LoggerFactory.getLogger(GameController.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private static final Marker SPRITES = MarkerFactory.getMarker("SPRITES");
    private static final Marker WS = MarkerFactory.getMarker("WS");
    private static GameController instance;
    private sGame game_pane = Drawer.getInstance().getGamePane();
    private Collisions collision = Collisions.getInstance();
    private Clock clock = Clock.getInstance();
    private KeyReader key = KeyReader.getInstance();
    private long key_delay = 1000;
    private long key_last_time = 0;
    private Thread thread;
    private SimpleList<Entity> entities = new SimpleList<>();
    public Wave wave = new Wave();
    private int waveCount = -1;
    private int waveSort = 0;
    private int waveSize = 64;
    private boolean paused;
    private boolean running;
    private boolean is_game_finished;

    private GameController(String msg) {
        super(msg);
        paused = false;
        running = true;
        is_game_finished = false;
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController("game");
            player = new Player();
            instance.thread = instance;
            instance.thread.start();
            logger.info(SYS, "Game thread started as: " + instance.thread.getName());
        }
        return instance;
    }

    public void abort() {
        running = false;
        logger.info(SYS, "Game aborted");
        end();
    }

    @Override
    public void run() {
        loadBackGround();
        getWave();
        while (running) {
            clock.ticks(60);

            if (isWaveClear()) {
                getWave();
            }

            event();

            if (isPaused()) {
                pause();
            }

            if (is_game_finished){
                gameEnd();
            }

            update();
            draw();
        }
        end();
    }

    private void loadBackGround() {
        logger.info(SPRITES, "Loading parallax backgrounds...");
        new Background(1, 0, Drawer.height, Drawer.width, Drawer.height, "file:res/img/backgrounds/parallax1/layer-1-sky.png", 1);
        new Background(2, 0, Drawer.height, Drawer.width, Drawer.height / 1.2, "file:res/img/backgrounds/parallax1/layer-2-mountain.png", 2);
    }

    private void getWave() {
        logger.info(WS, "Game ask for new wave: " + waveCount);
        if (waveCount != -1) WaveGenerator.deleteWave(wave.id);
        wave = WaveGenerator.getNewWave(waveSize);
        waveCount++;
        waveSize += 8;
    }

    private void getWaveSorted() {
        logger.info(WS, "Game ask for sorted wave: " + waveCount);
        WaveGenerator.getWaveSorted(wave, waveSort);
        waveSort++;
    }

    private void event() {
        long time = clock.getTime();

        // Checks the keys delay
        if (time - key_last_time > key_delay) {
            key_last_time = time;

            // Checks for key events
            if (!paused && (key.esc == 1 || key.pause == 1)) {
                paused = true;
            } else if (paused && (key.esc == 1 || key.pause == 1)) {
                paused = false;
            }
        }
        key.nunchukReadings();
    }

    public void pause() {
        logger.info(SYS, "Game Paused");
        game_pane.pause_menu.setVisible(true);
        game_pane.gamePane.setOpacity(0.4);
        while (isPaused()) {
            event();
            draw();
        }
        game_pane.pause_menu.setVisible(false);
        game_pane.gamePane.setOpacity(1);
        logger.info(SYS, "Game Resumed");
    }

    private void gameEnd() {
        logger.info(SYS, "Game has ended");
        game_pane.gamePane.setOpacity(0.4);
        if(player.isAlive()) {
            game_pane.winVBox.setVisible(true);
        }
        else {
            game_pane.loseVBox.setVisible(true);
        }
        while (is_game_finished()) {
            event();
            draw();
        }
    }

    private void update() {
        for (int i = 0; i < entities.getLarge(); i++) {
            entities.getByIndex(i).getValue().update();
        }
        verifyCollisions();
    }

    private void draw() {

    }

    public void deleteEntity(Entity entity) {
        //logger.info(SPRITES, "Removing from the game: " + entity.toString());

        // In case a Dragon dies
        if (entity.getClass() == Dragon.class) {
            wave.dragonDies((Dragon) entity);
            if (wave.getSize() > 0) getWaveSorted();
            logger.info(SPRITES, "WaveSize: " + String.valueOf(wave.getSize()));
        }

        // Deletes any entity passed throw
        entities.delete(entities.searchIndex(entity));
    }

    private void end() {
        logger.info(SYS, "Game thread stop: " + thread.getName());
        instance = null;
        thread.stop();
    }

    public void addEntity(Entity entity) {
        //logger.info(SPRITES, "Adding to the game: " + entity.toString());
        entities.addAtEnd(entity);
    }

    private void verifyCollisions() {
        if (player.isAlive()) {
            Boolean collision_player_with_dragon = collision.collide(player, collision.getDragons(), true);
            Boolean collision_player_with_dragonBullet = collision.collide(player, collision.getDragonBullets(), true);
            Boolean collision_player_with_hearts = collision.collide(player, collision.getHearts(), true);
            if (collision_player_with_dragon || collision_player_with_dragonBullet) {
                player.hit();
            }
            if (collision_player_with_hearts) player.setLives(player.getLives() + 1);
        }
        collision.collide(collision.getDragons(), collision.getPlayerBullets(), true, true);
        collision.collide(collision.getPlayerBullets(), collision.getDragonBullets(), true, true);
    }

    public void setWave(Wave wave) {
        this.wave = wave;
    }

    public int getWaveCount() {
        return waveCount;
    }

    public void setPaused(boolean pause) {
        paused = pause;
    }

    public boolean isWaveClear() {
        return wave.getSize() == 0;
    }

    public boolean isPaused() {
        return paused;
    }
    public boolean is_game_finished() {
        return is_game_finished;
    }
    public void setGameEnd(Boolean gameEnd) {
        logger.info(SYS, "setGameEnd");
        is_game_finished = gameEnd;
    }

    public boolean isGameRunning() {
        return running;
    }
}
