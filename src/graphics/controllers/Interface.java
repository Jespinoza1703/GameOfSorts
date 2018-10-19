package graphics.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class that invokes the methods in the Main class
 * Communicates the scenes controllers with Main
 */
public class Interface {

    private static Interface instance;
    private static Object main;  // Object to invoke
    private static String scene = "graphics/layouts/menu.fxml";  // Default Scene

    private Interface(Object main){
        Interface.main = main;
    }

    public static void init(Object main){
        if(instance == null){
            instance = new Interface(main);
        }
    }

    static void close(){
        try {
            Method close = main.getClass().getMethod("close");
            close.invoke(main, (Object[]) null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void switchScene(String scene_dir){
        Interface.scene = scene_dir;
        try {
            Method switchScene = main.getClass().getMethod("switchScene");
            switchScene.invoke(main, (Object[]) null);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String getScene(){
        return Interface.scene;
    }
}
