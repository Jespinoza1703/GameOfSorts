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

import java.io.IOException;

public class Main extends Application {

    private static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setResizable(false);
        window.setWidth(sScene.getWidth());
        window.setHeight(sScene.getHeight());
        window.setTitle("GameOfSorts");
        window.getIcons().add(new Image("file:res/img/icon.png"));
        Interface.init(this);
        switchScene();
        window.show();
        window.setOnCloseRequest(e-> close());
    }

    /**
     * Invokable method to set new Scenes in the window
     * @throws IOException in case the FXML readable is invalid
     */
    public static void switchScene() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(Interface.getScene()));
        Scene scene = new Scene(root);
        KeyReader.getInstance().setScene(scene);
        window.setScene(scene);
    }

    /**
     * Invokable method to kill all the threads
     */
    public static void close() {
        window.close();
        Platform.exit();
        System.exit(0);
    }
}
