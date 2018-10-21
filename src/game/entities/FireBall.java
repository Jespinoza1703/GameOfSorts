package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;

public class FireBall extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private Sprite sprite;
    private int xPoss;
    private int yPoss;
    private int xDir;
    private int yDir;
    private int xSpeed = 5;
    private int ySpeed = 5;

    public FireBall(int xPoss, int yPoss, int xDir, int yDir){
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.xDir = xDir;
        this.yDir = yDir;

        Drawer.getInstance().addDraw(this);
        GameController.getInstance().addEntity(this);
    }
    @Override
    public Sprite draw() {
        sprite = new Sprite(xPoss, yPoss,"file:res/img/icon.png");
        return sprite;
    }

    @Override
    public void update() {

        moveFire();
    }

    @Override
    public void destroy() {

    }

    public void moveFire(){
        xPoss += xSpeed * xDir;
        yPoss += ySpeed * yDir;

    }

    public double getxPoss() {
        return xPoss;
    }

    public double getyPoss() {
        return yPoss;
    }
}
