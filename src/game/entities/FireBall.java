package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.inputs.KeyReader;
import javafx.scene.shape.Rectangle;

public class FireBall extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private Rectangle sprite;
    private double xPoss;
    private double yPoss;
    private int yDir;
    private int xSpeed = 5;
    private int ySpeed = 5;

    public FireBall(int xPoss, int yPoss, int yDir){
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.yDir = yDir;

        Drawer.getInstance().addDraw(this);
        GameController.getInstance().addEntity(this);
    }
    @Override
    public Rectangle draw() {
        sprite = new Rectangle(xPoss, yPoss, 5, 5);
        return sprite;
    }

    @Override
    public void update() {
        moveFire();
    }

    public void moveFire(){
        xPoss += xSpeed;
        yPoss += ySpeed * yDir;

    }
}
