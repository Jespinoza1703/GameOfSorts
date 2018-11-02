package graphics.controllers;

import game.GameController;
import game.entities.Dragon;
import game.logic.trees.BTree;
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
    private BTree bTree = BTree.getBtree();
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


    public void getGraphicTree(){
        if (getTree().root == null) return;
        getGraphicTree(getTree().root,true);
    }

    private void getGraphicTree(BTree.Node node, Boolean isTail) {

      /*  logger.debug("Entry2");
        ImageView image = GameController.getInstance().wave.getDragonsList().get(0).getSprite().getSprite();
        AnchorPane pane = dragonPane;
        drawer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                logger.debug("Entry");
            }
        };
        drawer.start();




        for (int i = 0; i < node.numberOfKeys(); i++) {
            Comparable value = node.getKey(i);
            builder.append(value);
            if (i < node.numberOfKeys() - 1)
                builder.append(", ");
        }
        builder.append("\n");

        if (node.children != null) {
            for (int i = 0; i < node.numberOfChildren() - 1; i++) {
                BTree.Node<T> obj = node.getChild(i);
                builder.append(getString(obj, prefix + (isTail ? "    " : "|   "), false));
            }
            if (node.numberOfChildren() >= 1) {
                BTree.Node<T> obj = node.getChild(node.numberOfChildren() - 1);
                builder.append(getString(obj, prefix + (isTail ? "    " : "│   "), true));
            }
        }

        return builder.toString();
        */
        }






    public BTree getTree() {
        return bTree;
    }

    public void setbTree(BTree bTree) {
        this.bTree = bTree;
    }
}
