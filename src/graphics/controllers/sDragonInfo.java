package graphics.controllers;

import game.GameController;
import game.entities.Dragon;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.IOException;

public class sDragonInfo extends sScene {
    private Dragon actualDragon;
    private AnimationTimer drawer;

    private static Logger logger = LoggerFactory.getLogger(sDragonInfo.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    @FXML
    public Button returnBT;
    public Label nameLb;
    public Label ageLB;
    public Label rankLb;
    public Label fireRateLb;
    public Label livesLb;
    public Label actualSortLb;
    public AnchorPane dragonPane;


    @Override
    protected void initialize() throws IOException {







    }

    @Override
    void pressed_return() throws IOException {


    }

    @FXML
    public void handleReturnBTAction(ActionEvent event) {
        GameController.getInstance().setPaused(false);
        Stage stage = (Stage) returnBT.getScene().getWindow();
        stage.close();
    }



    public void updateDragonInfo(){
        nameLb.setText(actualDragon.getName());
        ageLB.setText(String.valueOf(actualDragon.getAge()));

    }

    public Dragon getActualDragon() {
        return actualDragon;
    }

    public void setActualDragon(Dragon actualDragon) {
        this.actualDragon = actualDragon;
    }


}
