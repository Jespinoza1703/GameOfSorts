package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import util.Clock;
import util.NameGenerator;

import java.util.ArrayList;

public class Dragon extends Entity {

    private String name;
    private Dragon parent;
    private double lives = 3; // [1, 3]
    private double fire_rate = (((Math.random())*15000));  // [10, 100]
    private int age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry
    private double xPoss, yPoss;
    private double dragonWidth = 80, dragonHeight = 65;
    private double xSpeed = 1;
    private long lastTime = 0;
    private KeyReader key;
    private Clock clock = Clock.getInstance();
    private Sprite sprite;
    private ArrayList<Sprite> movementAnimation = new ArrayList<>();
    private ArrayList<Sprite> deathAnimation = new ArrayList<>();
    private ArrayList<Sprite> currentAnimation = movementAnimation;
    private double animationTimer = 200;
    private double lastAnimationTime = 0;
    private int currentSprite = 0;
    private String state = "Moving"; // Moving / Dead
    private double currentPoss;


    public Dragon (double xPoss, double yPoss) {
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        sprite = loadImages();
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
        Collisions.getInstance().addDragon(this);
    }

    public Dragon(){

    }

    public Dragon(Dragon parent, int lives, int age, String rank) {
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
        if (state.equals("Moving")){
            currentAnimation = movementAnimation;
        } else if (state.equals("Dead")){
            currentAnimation = deathAnimation;
        }
    }

    @Override
    public void destroy() {
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
        Collisions.getInstance().deleteDragon(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Sprite draw() {
        int arrayLen = currentAnimation.size();
        long time = clock.getTime();
        if (time - lastAnimationTime > animationTimer){
            sprite = currentAnimation.get(currentSprite);
            lastAnimationTime = time;
            currentSprite = (currentSprite + 1) % currentAnimation.size();
        }
        if (currentSprite == arrayLen && state.equals("Dead")){
            destroy();
        }
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    /**
     * Loads images for dragon
     * @return Sprite
     */
    private Sprite loadImages(){
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dMovement2"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dMovement1"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dMovement3"));
        deathAnimation.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dDeath1"));
        deathAnimation.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dDeath2"));
        deathAnimation.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dDeath3"));
        return movementAnimation.get(0);
    }

    @Override
    public void hit(){
        lives--;
        if(lives <= 0){
            dies();
        }
    }

    private void shoot(){
        FireBall fireBall = new FireBall(xPoss, yPoss, 33, 11,-1, 0);
        Collisions.getInstance().addDragonBullets(fireBall);
    }

    private void dies(){
        state = "Dead";
    }

    private void pressed(){

    }

    private void move(){
        state = "Moving";
        xPoss -= xSpeed;
        currentPoss = xPoss;
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

    /** Getters and Setters **/

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
