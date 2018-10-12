package game.draw;

import game.logic.lists.SimpleList;
import javafx.scene.layout.Pane;

/**
 * Singleton class that manages the objects to be drawn in the gamePane
 * @author José Acuña
 */
public class Drawer <T>{

    private static Drawer instance;
    private SimpleList<T> draws;
    private Pane drawPane;

    private Drawer(Pane pane){
        drawPane = pane;
    }

    public static void init(Pane pane){
        if (instance == null){
            instance = new Drawer(pane);
        }
    }

    public static Drawer getInstance(){
        return instance;
    }

    public void draw(){

    }

    public void addDraw(T draw){
        draws.addAtEnd(draw);
    }
}
