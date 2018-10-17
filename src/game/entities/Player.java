package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.inputs.KeyReader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import util.Math;

public class Player extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private int lives = 3;
    private int xSpeed = 0;
    private int ySpeed = 0;
    private int xMaxSpeed = 10;
    private int yMaxSpeed = 10;
    private int xAcc = 2;
    private int yAcc = 3;
    private int xPoss = 200;
    private int yPoss = 200;
    private int fire_rate = 90;
    private int damage = 1;
    private String state = "Moving"; // Moving / Dead / Dashing
    private Rectangle sprite;

    public Player(){
        Drawer.getInstance().addDraw(this);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public void update(){
        move();
    }

    @Override
    public Rectangle draw() {
        sprite = new Rectangle(xPoss, yPoss, 20, 20);
        return sprite;
    }

    private void hit(){
        lives--;
        if(lives <= 0){
            dead();
        }
    }

    private void heal(){

    }

    private void shoot(){

    }

    private void dead(){

    }

    private void move(){
        var xMove = key.right - key.left;
        var yMove = key.up - key.down;
        xSpeed = Math.clamp(xSpeed += xAcc * xMove, -xMaxSpeed, xMaxSpeed);
        ySpeed = Math.clamp(ySpeed -= yAcc * yMove, -yMaxSpeed, yMaxSpeed);
        xPoss += xSpeed;
        yPoss += ySpeed;
    }
}
