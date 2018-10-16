package game.inputs;

import javafx.scene.Scene;

public class KeyReader {

    private Scene scene;
    private static KeyReader instance;
    public static int k_right;
    public static int k_left;
    public static int k_up;
    public static int k_down;
    public static int k_space;
    public static int k_shift;


    private KeyReader(){

    }

    public static KeyReader getInstance(){
        if (instance == null) {
            instance = new KeyReader();
        }
        return instance;
    }

    private void keyPressed() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case D: k_right = 1; break;
                case A: k_left = 1; break;
                case W: k_up = 1; break;
                case S: k_down = 1; break;
                case SPACE: k_space = 1; break;
                case SHIFT: k_shift = 1; break;
            }
        });
    }


    private void keyReleased() {
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case D: k_right = 0; break;
                case A: k_left = 0; break;
                case W: k_up = 0; break;
                case S: k_down = 0; break;
                case SPACE: k_space = 0; break;
                case SHIFT: k_shift = 0; break;
            }
        });
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        keyPressed();
        keyReleased();
    }
}
