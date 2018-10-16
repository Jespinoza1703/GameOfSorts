package graphics.controllers;

import game.GameController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class sDevTest extends sScene{

    @FXML
    Pane gamePane;

    @Override
    protected void initialize() throws IOException {
        GameController.getInstance().start(gamePane);
    }

    @Override
    void pressed_return() throws IOException {
        GameController.getInstance().abort();
        Interface.switchScene("graphics/layouts/menu.fxml");
    }
}
