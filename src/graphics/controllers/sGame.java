package graphics.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class sGame extends sScene{

    @FXML
    public Pane gamePane;

    @Override
    protected void initialize() throws IOException {

    }

    @Override
    void pressed_return() throws IOException {
        Interface.switchScene("graphics/layouts/menu.fxml");
    }
}
