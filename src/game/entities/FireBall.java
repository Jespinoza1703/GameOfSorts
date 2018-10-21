package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.KeyReader;

public class FireBall extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private Sprite sprite;
    private double xPoss;
    private double yPoss;
    private double xDir;
    private double yDir;
    private double xSpeed = 15;
    private double ySpeed = 15;
    private double fireWidth;
    private double fireHeight;

    public FireBall(double xPoss, double yPoss, double fireWidth, double fireHeight, double xDir, double yDir){
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.fireWidth = fireWidth;
        this.fireHeight = fireHeight;
        this.xDir = xDir;
        this.yDir = yDir;

        Drawer.getInstance().addDrawAtBegining(this);
        GameController.getInstance().addEntity(this);
    }
    @Override
    public Sprite draw() {
        sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight, "file:res/img/entities/fireball/Fireball1");
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
