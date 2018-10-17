package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.inputs.KeyReader;
import util.Math;

public class Player extends Entity {

    private static Player instance;
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

    private Player(){

    }

    public static Player getInstance(){
        if (instance == null){
            instance = new Player();
        }
        return instance;
    }

    public void generatePlayer(){
        Drawer.getInstance().addDraw(this);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public void update(){
        move();
    }

    @Override
    public void draw() {

    }

    private void hit(){

    }

    private void heal(){

    }

    private void shoot(){

    }

    private void move(){
        var move = key.right - key.left;
        xSpeed = Math.clamp(xSpeed += xAcc * move, -xMaxSpeed, xMaxSpeed);
        ySpeed = Math.clamp(ySpeed += yAcc * move, -yMaxSpeed, yMaxSpeed);
        xPoss += xSpeed;
        yPoss += ySpeed;
    }
}
