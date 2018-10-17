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
    private SimpleList<Entity> last_draws = draws;
    private Pane drawPane;

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
        AnimationTimer drawer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                instance.draw();
            }
        };
        drawer.start();
    }

    private void draw(){
        for (int i = 0; i < last_draws.getLarge(); i++){
            Rectangle sprite = last_draws.getByIndex(i).getValue().draw();
            drawPane.getChildren().removeAll(sprite);
        }
        for (int i = 0; i < draws.getLarge(); i++){
            Rectangle sprite = draws.getByIndex(i).getValue().draw();
            drawPane.getChildren().addAll(sprite);
        }
        last_draws = draws;
    }

    public void addDraw(Entity draw){
        draws.addAtEnd(draw);
    }
}
