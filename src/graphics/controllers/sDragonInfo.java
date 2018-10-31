package graphics.controllers;

import game.GameController;
import game.draw.Drawer;
import game.event.handler.Collisions;
import javafx.fxml.FXML;

import java.io.IOException;

public class sDragonInfo extends sScene {
    @Override
    protected void initialize() throws IOException {

    }

    @Override
    void pressed_return() throws IOException {

    }

    @FXML
    void resume_Game()throws IOException{
        GameController.getInstance().abort();
        Drawer.getInstance().abort();
        Collisions.getInstance().abort();
        Interface.switchScene("graphics/layouts/game.fxml");
    }
}
