package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import util.Clock;
import util.NameGenerator;

public class Dragon extends Entity {

    private String name;
    private Dragon parent;
    private double lives; // [1, 3]
    private double fire_rate = (((Math.random())*15000));  // [10, 100]
    private double age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry
    private double xPoss;
    private double yPoss;
    private double dragonWidth = 80;
    private double dragonHeight = 65;
    private double xSpeed = 1;
    private long lastTime = 0;
    private KeyReader key;
    private Clock clock = Clock.getInstance();
    private Sprite sprite;


    public Dragon (double xPoss, double yPoss) {
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
        Collisions.getInstance().addDragon(this);
        this.xPoss = xPoss;
        this.yPoss = yPoss;
    }

    public Dragon(){

    }

    public Dragon(Dragon parent, double lives, double age, String rank) {
        this.name = (NameGenerator.generateName());
        this.fire_rate = (((Math.random())*100));
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
        sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight, "file:res/img/entities/dragon/Dragon1");
        return sprite;
    }

    private void hit(){

    }

    private void shoot(){
        FireBall fireBall = new FireBall(xPoss, yPoss, 15, 15,-1, 0);
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

    public double getLives() {
        return lives;
    }

    public void setLives(double lives) {
        this.lives = lives;
    }

    public double getFire_rate() {
        return fire_rate;
    }

    public void setFire_rate(double fire_rate) {
        this.fire_rate = fire_rate;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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
