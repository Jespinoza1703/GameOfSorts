package game.draw;

import game.GameController;
import game.entities.Background;
import game.entities.Entity;
import game.logic.lists.SimpleList;
import graphics.controllers.sGame;
import graphics.controllers.sScene;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Singleton class that manages the objects to be drawn in the gamePane
 * @author José Acuña
 */
public class Drawer {

    private static Logger logger = LoggerFactory.getLogger(Drawer.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    public static double width = sScene.getWidth();
    public static double height = sScene.getHeight();
    private static Drawer instance;
    private sGame gamePane;
    private SimpleList<Entity> draws = new SimpleList<>();  // List of Entities to draw
    private SimpleList<Background> bg1 = new SimpleList<>();
    private SimpleList<Background> bg2 = new SimpleList<>();
    private SimpleList<Background> bg3 = new SimpleList<>();
    private Pane drawPane;  // Place to draw the entities
    private AnimationTimer drawer;  //Access to JavaFx Thread

    private Drawer(Pane pane){
        drawPane = pane;
    }

    public static Drawer getInstance(){
        return instance;
    }

    public static void init(Pane pane){
        if (instance == null){
            instance = new Drawer(pane);
        }

        // Access to the JavaFx Thread
        instance.drawer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                instance.draw();
            }
        };
        instance.drawer.start();
        logger.info(SYS, "Drawer thread start as: " + instance.drawer.toString());
    }

    /**
     * This method adds in the drawPane the objects that are in the draws:SimpleList after cleaning it
     */
    private synchronized void draw(){
        drawPane.getChildren().clear();
        drawBG();
        drawEntities();
        drawLife();
    }

    private void drawEntities(){
        for (int i = 0; i < draws.getLarge(); i++){
            ImageView sprite = draws.getByIndex(i).getValue().draw().getSprite();
            boolean contains = drawPane.getChildren().contains(sprite);
            if (!contains) drawPane.getChildren().addAll(sprite);
        }
    }

    private void drawBG() {
        for (int i = 0; i < bg1.getLarge(); i++){
            ImageView sprite = bg1.getByIndex(i).getValue().draw().getSprite();
            drawPane.getChildren().addAll(sprite);
        }
        for (int i = 0; i < bg2.getLarge(); i++){
            ImageView sprite = bg2.getByIndex(i).getValue().draw().getSprite();
            drawPane.getChildren().addAll(sprite);
        }
        for (int i = 0; i < bg3.getLarge(); i++){
            ImageView sprite = bg3.getByIndex(i).getValue().draw().getSprite();
            drawPane.getChildren().addAll(sprite);
        }
    }

    private void drawLife(){
        int hearts = gamePane.lifeBox.getChildren().size();
        int life = GameController.player.getLives();
        if (hearts < life) {
            Sprite heart = new Sprite(0, 0, 20, 20, "file:res/img/entities/life/heart.png");
            gamePane.lifeBox.getChildren().addAll(heart.getSprite());
        }
        if (hearts > life) gamePane.lifeBox.getChildren().remove(hearts-1);
    }

    public void addDrawAtEnd(Entity draw){
        draws.addAtEnd(draw);
    }

    public void addDrawAtBeginning(Entity draw){
        draws.addAtBeginning(draw);
    }

    public void deleteEntity(Entity draw){
        draws.delete(draws.searchIndex(draw));
    }

    public void addBackGround(Background bg, int level){
        if (level == 1) bg1.addAtEnd(bg);
        if (level == 2) bg2.addAtEnd(bg);
        if (level == 3) bg3.addAtEnd(bg);
    }

    public void abort(){
        logger.info(SYS, "Drawer aborted");
        drawer.stop();
        instance = null;
    }

    public sGame getGamePane() {
        return gamePane;
    }

    public void setGamePane(sGame gamePane) {
        this.gamePane = gamePane;
    }

    public Pane getDrawPane() {
        return drawPane;
    }

    public void setDrawPane(Pane drawPane) {
        this.drawPane = drawPane;
    }
}
