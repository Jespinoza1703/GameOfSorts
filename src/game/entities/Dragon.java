package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import javafx.scene.shape.Rectangle;
import util.Clock;
import util.NameGenerator;

public class Dragon extends Entity {

    private String name;
    private Dragon parent;
    private int lives; // [1, 3]
    private int fire_rate = ((int)((Math.random())*15000));  // [10, 100]
    private int age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry
    private int xPoss;
    private int yPoss;
    private double xSpeed = 1;
    private long lastTime = 0;
    private KeyReader key;
    private Clock clock = Clock.getInstance();
    private Sprite sprite;


    public Dragon (int xPoss, int yPoss) {
        Drawer.getInstance().addDraw(this);
        GameController.getInstance().addEntity(this);
        Collisions.getInstance().addDragon(this);
        this.xPoss = xPoss;
        this.yPoss = yPoss;
    }

    public Dragon(){

    }

    public Dragon(Dragon parent, int lives, int age, String rank) {
        this.name = (NameGenerator.generateName());
        this.fire_rate = ((int)((Math.random())*100));
        this.age = age;
        this.parent = parent;
        this.lives = lives;
        this.rank = rank;
    }

    @Override
    public void update(){
        move();

        if (canShoot()){
            shoot();
        }
    }


    @Override
    public void destroy() {

    }

    @Override
    public Sprite draw() {
        sprite = new Sprite(xPoss, yPoss,"file:res/img/entities/dragon/Dragon1");
        return sprite;
    }

    private void hit(){

    }

    private void shoot(){
        FireBall fireBall = new FireBall(xPoss, yPoss, -1, 0);
        Collisions.getInstance().addDragonBullets(fireBall);
    }

    private void dies(){

    }

    private void pressed(){

    }

    private void move(){
        xPoss -= xSpeed;
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

    /** Getters andSetters **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dragon getParent() {
        return parent;
    }

    public void setParent(Dragon parent) {
        this.parent = parent;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getFire_rate() {
        return fire_rate;
    }

    public void setFire_rate(int fire_rate) {
        this.fire_rate = fire_rate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getxPoss() {
        return xPoss;
    }

    public void setxPoss(int xPoss) {
        this.xPoss = xPoss;
    }

    public int getyPoss() {
        return yPoss;
    }

    public void setyPoss(int yPoss) {
        this.yPoss = yPoss;
    }
}
