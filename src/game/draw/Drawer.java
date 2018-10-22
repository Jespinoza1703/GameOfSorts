package game.draw;

import game.entities.Entity;
import game.logic.lists.SimpleList;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Singleton class that manages the objects to be drawn in the gamePane
 * @author José Acuña
 */
public class Drawer {

    private static Drawer instance;
    private SimpleList<Entity> draws = new SimpleList<>();  // List of Entities to draw
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
    }

    /**
     * This method adds in the drawPane the objects that are in the draws:SimpleList after cleaning it
     */
    private void draw(){
        drawPane.getChildren().clear();
        for (int i = 0; i < draws.getLarge(); i++){
            ImageView sprite = draws.getByIndex(i).getValue().draw().getSprite();
            drawPane.getChildren().addAll(sprite);
        }
    }

    public void addDrawAtEnd(Entity draw){
        draws.addAtEnd(draw);
    }

    public void addDrawAtBegining(Entity draw){
        draws.addAtBeginning(draw);
    }

    public void deleteEntity(Entity draw){
        draws.delete(draws.searchIndex(draw));
    }

    public void abort(){
        drawer.stop();
        instance = null;
    }
}
