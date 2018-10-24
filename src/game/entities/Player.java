package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import util.Clock;
import util.Math;

import java.util.ArrayList;

public class Player extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private Clock clock = Clock.getInstance();
    private int lives = 3;
    private int xSpeed = 0, ySpeed = 0;
    private int xMaxSpeed = 8, yMaxSpeed = 8;
    private int xAcc = 1, yAcc = 2;
    private double xPoss = 200, yPoss = 200;
    private int playerWidth = 150, playerHeight = 200;
    private int fire_rate = 500;
    private int damage = 1;
    private long lastTime = 0;
    private String state = "Moving"; // Moving / Dead / Dashing
    private ArrayList<Sprite> movementAnimation = new ArrayList<>();
    private ArrayList<Sprite> deathAnimation = new ArrayList<>();
    private ArrayList<Sprite> dashAnimation = new ArrayList<>();
    private ArrayList<Sprite> currentAnimation = new ArrayList<>();
    private Sprite sprite = loadImages();
    private int animationTimer = 200;
    private double lastAnimationTime = 0;
    private int currentSprite = 0;

    public Player(){
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public void update(){
        move();

        if (key.shoot == 1 && canShoot()){
            shoot();
        }
        if (state.equals("Moving")){
            currentAnimation = movementAnimation;
        } else if(state.equals("Dead")){
            currentAnimation = deathAnimation;
        } else if (state.equals("Dashing")){
            currentAnimation = dashAnimation;
        }
    }

    @Override
    public void destroy() {
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Sprite draw() {
        long time = clock.getTime();
        if (time - lastAnimationTime > animationTimer){
            sprite = movementAnimation.get(currentSprite);
            lastAnimationTime = time;
            currentSprite = (currentSprite + 1) % movementAnimation.size();
        }
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    private Sprite loadImages(){
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin1"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin2"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin3"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin4"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin5"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin6"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin7"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, playerWidth, playerHeight,
                "file:res/img/entities/griffin/griffin8"));
        return movementAnimation.get(0);

    }

    @Override
    public void hit(){
        lives--;
        if(lives <= 0){
            dies();
        }
    }

    @Override
    public void setLives(int lives) {

    }

    private void heal(){

    }

    private void shoot(){
        var yDirection = key.arrow_down - key.arrow_up;
        FireBall fireBall = new FireBall(xPoss, yPoss, 50, 17,1, yDirection);
        Collisions.getInstance().addPlayerBullets(fireBall);
    }

    private void dies(){
        new BulletExplosion(xPoss, yPoss, playerWidth, playerHeight);
        destroy();
    }

    private void move(){
        var xMove = key.right - key.left;
        var yMove = key.up - key.down;

        // Adds gravity
        if(yMove < 0){
            yAcc = 3;
            yMaxSpeed = 10;
        }
        if(yMove > 0){
            yAcc = 2;
            yMaxSpeed = 7;
        }

        // Adds air friction
        if (xMove == 0){
            xSpeed =  Math.approach(xSpeed, 0, 1);
        }
        if (yMove == 0){
            ySpeed =  Math.approach(ySpeed, 0, 1);
        }

        // Calculates current position
        xSpeed = Math.clamp(xSpeed += xAcc * xMove, -xMaxSpeed, xMaxSpeed);
        ySpeed = Math.clamp(ySpeed -= yAcc * yMove, -yMaxSpeed, yMaxSpeed);
        xPoss += xSpeed;
        yPoss += ySpeed;

        // Calculates boundaries
        var height = Drawer.height;
        var width = Drawer.width;
        var spriteHH = sprite.getHeight() / 2;
        var spriteHW = sprite.getWidth() / 2;
        if (yPoss - spriteHH < 0) yPoss = 0 + spriteHH;
        if (yPoss + spriteHH > height) yPoss = height - spriteHH;
        if (xPoss - spriteHW < 0) xPoss = 0 + spriteHW;
        if (xPoss + spriteHW > width) xPoss = width - spriteHW;
    }

    private Boolean canShoot(){
        Boolean result = false;
        long time = clock.getTime();
        if (time - lastTime > fire_rate){
            result = true;
            lastTime = time;
        }
        return result;

    }

    public double getxPoss() {
        return xPoss;
    }

    public void setxPoss(double xPoss) {
        this.xPoss = xPoss;
    }

    public double getyPoss() {
        return yPoss;
    }

    public void setyPoss(double yPoss) {
        this.yPoss = yPoss;
    }
}
