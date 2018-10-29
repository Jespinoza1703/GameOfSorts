package graphics.controllers;

import client.Wave;
import client.WaveGenerator;
import game.GameController;
import game.draw.Drawer;
import game.event.handler.Collisions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class sGame extends sScene{

    @FXML
    public Pane gamePane;
    @FXML
    public VBox pause_menu;
    @FXML
    public HBox lifeBox;
    @FXML
    public Label waveCount;

    @Override
    protected void initialize() throws IOException {
        Drawer.init(gamePane);
        Drawer.getInstance().setGamePane(this);
        GameController.getInstance();
    }

    @Override
    void pressed_return() throws IOException {
        GameController.getInstance().abort();
        Drawer.getInstance().abort();
        Collisions.getInstance().abort();
        Interface.switchScene("graphics/layouts/menu.fxml");
    }

    @FXML
    void pressed_resume(){
        GameController.getInstance().setPaused(false);
    }

    @FXML
    void pressed_pause(){
        boolean paused = GameController.getInstance().isPaused();
        GameController.getInstance().setPaused(!paused);
    }

    @FXML
    void pressed_exit(){
        Interface.close();
    }
}
