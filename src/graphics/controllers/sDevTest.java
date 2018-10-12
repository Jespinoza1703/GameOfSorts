package graphics.controllers;

import game.GameController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class sDevTest extends  sScene{

    @FXML
    AnchorPane gamePane;

    @Override
    protected void initialize() throws IOException {
        GameController.getInstance().start();
    }

    @Override
    void pressed_return() throws IOException {
        Interface.switchScene("graphics/layouts/menu.fxml");
    }
}
