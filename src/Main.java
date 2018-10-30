import game.event.handler.inputs.KeyReader;
import graphics.controllers.Interface;
import graphics.controllers.sScene;
import graphics.sound.Sound;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main extends Application {

    private static Stage window;
    private static Logger logger = LoggerFactory.getLogger("Main");

    /**
     * Launch the app
     * @param args
     */
    public static void main(String[] args) {
        logger.debug("App launch");
        launch(args);
    }

    /**
     * Start the stages
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setResizable(false);
        logger.debug("Window not resizable set it");
        window.setWidth(sScene.getWidth());
        window.setHeight(sScene.getHeight());
        window.setTitle("GameOfSorts");
        window.getIcons().add(new Image("file:res/img/icon.png"));
        Interface.init(this);
        switchScene();
        //Sound.play("res/sounds/ocarina.wav", 1);
        Sound.startSong();
        window.show();
        window.setOnCloseRequest(e-> close());
    }

    /**
     * Invokable method to set new Scenes in the window
     * @throws IOException in case the FXML readable is invalid
     */
    public static void switchScene() throws IOException {
        logger.debug("Scene has been swathed");
        Parent root = FXMLLoader.load(Main.class.getResource(Interface.getScene()));
        Scene scene = new Scene(root);
        KeyReader.getInstance().setScene(scene);
        window.setScene(scene);
    }

    /**
     * Invokable method to kill all the threads
     */
    public static void close() {
        logger.debug("All threads has been killed");
        window.close();
        Platform.exit();
        System.exit(0);
    }
}
