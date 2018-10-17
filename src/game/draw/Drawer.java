package game.draw;

import game.entities.Entity;
import game.logic.lists.SimpleList;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Singleton class that manages the objects to be drawn in the gamePane
 * @author José Acuña
 */
public class Drawer {

    private static Drawer instance;
    private SimpleList<Entity> draws = new SimpleList<>();
    private Pane drawPane;
    private AnimationTimer drawer;

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
        instance.drawer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                instance.draw();
            }
        };
        instance.drawer.start();
    }

    /**
     * This method draws constantly in the drawPane the objects that are in the draws:SimpleList
     */
    private void draw(){
        drawPane.getChildren().clear();
        for (int i = 0; i < draws.getLarge(); i++){
            Rectangle sprite = draws.getByIndex(i).getValue().draw();
            drawPane.getChildren().addAll(sprite);
        }
    }

    public void addDraw(Entity draw){
        draws.addAtEnd(draw);
    }

    public void abort(){
        drawer.stop();
        instance = null;
    }
}
