package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import util.Clock;

import java.util.ArrayList;

public class FireBall extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private Clock clock = Clock.getInstance();
    private Sprite sprite;
    private double xPoss, yPoss;
    private double xDir, yDir;
    private double xSpeed = 3, ySpeed = 3;
    private double fireWidth, fireHeight;
    private ArrayList<Sprite> animations = new ArrayList<>();
    private double animationTimer = 200;
    private double lastAnimationTime = 0;
    private int currentSprite = 0;

    public FireBall(double xPoss, double yPoss, double fireWidth, double fireHeight, double xDir, double yDir){
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.fireWidth = fireWidth;
        this.fireHeight = fireHeight;
        this.xDir = xDir;
        this.yDir = yDir;
        sprite = loadImages();
        Drawer.getInstance().addDrawAtBegining(this);
        GameController.getInstance().addEntity(this);
    }
    @Override
    public Sprite draw() {
        long time = clock.getTime();
        if (time - lastAnimationTime > animationTimer){
            sprite = animations.get(currentSprite);
            lastAnimationTime = time;
            currentSprite = (currentSprite + 1) % animations.size();
        }
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    private Sprite loadImages(){
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball1"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball1"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball"));
        return animations.get(0);

    }

    @Override
    public void update() {
        moveFire();
    }

    @Override
    public void destroy() {
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
        Collisions.getInstance().deleteBullets(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
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
