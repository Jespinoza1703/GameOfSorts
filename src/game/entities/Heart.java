package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.Collisions;

public class Heart extends Entity {

    private double xPoss, yPoss;
    private double xSpeed = 6, ySpeed = 3;
    private Sprite sprite;
    private int oscillationTime = 20;
    private int oscillationDuration = oscillationTime;


    public Heart(double x, double y){
        xPoss = x;
        yPoss = y;
        sprite = new Sprite(x, y, 40, 40, "file:res/img/entities/life/heart.png");
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
        Collisions.getInstance().addHeart(this);
    }


    @Override
    public Sprite draw() {
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    @Override
    public void update() {
        angularYSpeed();
        xPoss -= xSpeed;
        yPoss += ySpeed;
    }

    @Override
    public void destroy() {
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
        Collisions.getInstance().deleteHeart(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void hit() {
        destroy();
    }

    @Override
    public void setLives(int lives) {

    }

    private void angularYSpeed(){
        oscillationDuration--;
        if (oscillationDuration <= 0) {
            ySpeed *= -1;
            oscillationDuration = oscillationTime;
        }
    }
}
