package game.graphics.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Interface {

    private static Interface instance;
    private static Object main;
    private static String scene = "game/graphics/layouts/menu.fxml";

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
            Method setScene = main.getClass().getMethod("setScene");
            setScene.invoke(main, (Object[]) null);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String getScene(){
        return Interface.scene;
    }
}
