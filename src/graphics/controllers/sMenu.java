package graphics.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;

public class sMenu extends sScene{

    @FXML
    AnchorPane anchorPane;

    @Override
    protected void initialize() throws IOException {
        Image img = new Image("file:res/img/backgrounds/bg/bg4.jpg");
        BackgroundSize size = new BackgroundSize(sScene.getWidth(), sScene.getHeight(), false, false, false, false);
        BackgroundImage bgIMG = new BackgroundImage(img, null, null, BackgroundPosition.CENTER, size);
        Background bg = new Background(bgIMG);
        anchorPane.setBackground(bg);
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
