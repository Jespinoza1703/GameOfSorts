package graphics.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Class parent of the Scenes
 * Default structure that the scene controllers must follow
 */
public abstract class sScene {

    /**
     * 800x600
     * 1024x768
     * 1280x960
     * 1400x1050
     * 1680x1050
     * 1920x1080
     */
    private static int width = 800;
    private static int height = 600;

    @FXML
    protected abstract void initialize() throws IOException;

    @FXML
    abstract void pressed_return() throws IOException;


    /** Getters and Setters **/

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        sScene.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        sScene.height = height;
    }
}