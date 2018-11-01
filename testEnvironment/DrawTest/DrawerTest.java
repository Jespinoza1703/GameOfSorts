package DrawTest;

import game.draw.Drawer;
import graphics.controllers.sGame;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrawerTest {
    @Test
    void initTest(){
        Drawer.init(new Pane());
        Assertions.assertNotNull(Drawer.getInstance());
    }
    @Test
    void getSetDrawPaneTest(){
        Drawer.init(new Pane());
        Pane pane = new Pane();
        Drawer.getInstance().setDrawPane(pane);
        Assertions.assertSame(pane, Drawer.getInstance().getDrawPane());
    }

    @Test
    void GetSetGamePane(){
        Drawer.init(new Pane());
        sGame sGame = new sGame();
        Drawer.getInstance().setGamePane(sGame);
        Assertions.assertSame(sGame, Drawer.getInstance().getGamePane());
    }
    @Test
    void abortTest(){
        Drawer.init(new Pane());
        Drawer.getInstance().abort();
        Assertions.assertNull(Drawer.getInstance());
    }

}
