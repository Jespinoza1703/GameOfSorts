package graphics.controllers;

import client.Wave;
import client.WaveGenerator;
import game.GameController;
import game.draw.Drawer;
import game.event.handler.Collisions;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class sDevTest extends sScene{

    @FXML
    Pane gamePane;

    @Override
    protected void initialize() throws IOException {
        Drawer.init(gamePane);
        Wave wave = new Wave(0, 24);
        GameController.getInstance().setWave(wave);
        WaveGenerator.listWave(wave);

    }

    @Override
    void pressed_return() throws IOException {
        GameController.getInstance().abort();
        Drawer.getInstance().abort();
        Collisions.getInstance().abort();
        Interface.switchScene("graphics/layouts/menu.fxml");
    }
}
