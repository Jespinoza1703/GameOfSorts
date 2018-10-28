package game.draw;

import game.entities.Background;
import game.entities.Entity;
import game.logic.lists.SimpleList;
import graphics.controllers.sScene;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Singleton class that manages the objects to be drawn in the gamePane
 * @author José Acuña
 */
public class Drawer {

    public static double width = sScene.getWidth();
    public static double height = sScene.getHeight();
    private static Drawer instance;
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
                //width = pane.getWidth();
                //height = pane.getHeight();
                instance.draw();
            }
        };
        instance.drawer.start();
    }

    /**
     * This method adds in the drawPane the objects that are in the draws:SimpleList after cleaning it
     */
    private void draw(){
        drawPane.getChildren().clear();
        drawBG();
        for (int i = 0; i < draws.getLarge(); i++){
            ImageView sprite = draws.getByIndex(i).getValue().draw().getSprite();
            drawPane.getChildren().addAll(sprite);
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
        drawer.stop();
        instance = null;
    }
}
