package graphics.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class sMenu extends sScene{

    @Override
    protected void initialize() throws IOException {

    }

    @Override
    void pressed_return() throws IOException {
        Interface.close();
    }

    @FXML
    void pressed_play(){
        Interface.switchScene("graphics/layouts/game.fxml");
    }

    @FXML
    void pressed_dev_test(){
        Interface.switchScene("graphics/layouts/dev_test.fxml");
    }
}
