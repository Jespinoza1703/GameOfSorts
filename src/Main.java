import game.event.handler.inputs.KeyReader;
import graphics.controllers.Interface;
import graphics.controllers.sScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.IOException;

public class Main extends Application {

    private static Stage window;
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private static final Marker GUI = MarkerFactory.getMarker("GUI");

    public static void main(String[] args) {
        logger.info(SYS, "Running JavaFx Application");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setResizable(false);
        logger.info(GUI, "Window resizable set: false");
        window.setWidth(sScene.getWidth());
        window.setHeight(sScene.getHeight());
        logger.info(GUI, "Window dimensions set: " + (int)window.getWidth() + "x" + + (int)window.getHeight());
        window.setTitle("GameOfSorts");
        window.getIcons().add(new Image("file:res/img/icon.png"));
        Interface.init(this);
        switchScene();
        window.show();
        window.setOnCloseRequest(e-> close());
    }

    /**
     * Invokable method to set new Scenes in the window
     */
    public static void switchScene() {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(Interface.getScene()));
            Scene scene = new Scene(root);
            KeyReader.getInstance().setScene(scene);
            window.setScene(scene);
            logger.info(GUI, "New Scene has been set: " + scene.getRoot().getId());
        } catch (IOException e) {
            logger.error(GUI, "Failed to load fxml resource" + e);
        }

    }

    /**
     * Invokable method to kill all the threads
     */
    public static void close() {
        logger.info(SYS, "Program closed");
        window.close();
        Platform.exit();
        System.exit(0);
    }
}
