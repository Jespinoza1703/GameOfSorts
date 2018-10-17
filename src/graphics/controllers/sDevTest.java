package graphics.controllers;

import game.GameController;
import game.draw.Drawer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class sDevTest extends sScene{

    @FXML
    Pane gamePane;

    @Override
    protected void initialize() throws IOException {
        Drawer.init(gamePane);
        GameController.getInstance();
    }

    @Override
    void pressed_return() throws IOException {
        GameController.getInstance().abort();
        Interface.switchScene("graphics/layouts/menu.fxml");
    }
}
