package game.graphics.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public abstract class sScene {

    private static int width = 1280;
    private static int height = 960;

    @FXML
    protected abstract void initialize() throws IOException;

    @FXML
    abstract void pressed_return() throws IOException;

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