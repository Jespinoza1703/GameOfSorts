package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import graphics.sound.Sound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Clock;

import java.util.ArrayList;

public class Dragon extends Entity{

    private String name;
    private int parentAge;
    private int lives; // [1, 3]
    private int fire_rate = (int) (Math.random() * 15000);  // [10, 100]
    private int age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry
    private double xPoss, yPoss;
    private double dragonWidth = 80, dragonHeight = 65;
    private double xSpeed = 1;
    private long lastTime = 0;
    private KeyReader key;
    private Clock clock = Clock.getInstance();
    private Sprite sprite;
    private ArrayList<Sprite> movementAnimations = new ArrayList<>();
    private double animationTimer = 200;
    private double lastAnimationTime = 0;
    private int currentSprite = 0;
    private Boolean playerCollision = false;
    private static Logger logger = LoggerFactory.getLogger("Dragon");


    /**
     * Constructor with grafic coordinates
     * @param xPoss
     * @param yPoss
     */
    public Dragon(double xPoss, double yPoss) {
        logger.debug("Created new dragon");
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        sprite = loadImages();
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
        Collisions.getInstance().addDragon(this);
    }

    /**
     * Basic constructor
     */
    public Dragon() {

        logger.debug("Created new dragon");
    }


    public Dragon(int parentAge, int age, String rank) {
        logger.debug("Created new dragon");
        this.parentAge = parentAge;
        this.age = age;
        this.rank = rank;
    }

    /**
     * Update the dragon position
     */
    @Override
    public void update() {
        move();

        if (canShoot()) {
            shoot();
        }
    }


    /**
     * Destroys the dragon in the GUI
     */
    @Override
    public void destroy() {
        logger.debug(this + "has been destroyed");
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
        Collisions.getInstance().deleteDragon(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Draw the figure Dragon in the GUI
     * @return Dragon to draw
     */
    @Override
    public Sprite draw() {
        long time = clock.getTime();
        if (time - lastAnimationTime > animationTimer) {
            sprite = movementAnimations.get(currentSprite);
            lastAnimationTime = time;
            currentSprite = (currentSprite + 1) % movementAnimations.size();
        }
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    /**
     * Loads images for dragon
     *
     * @return Sprite
     */
    private Sprite loadImages() {
        movementAnimations.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dMovement2"));
        movementAnimations.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dMovement1"));
        movementAnimations.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight,
                "file:res/img/entities/dragon/dMovement3"));
        return movementAnimations.get(0);
    }

    /**
     * Update dragon when received a shoot
     */
    @Override
    public void hit(){
        logger.debug(this + "has been hit");
        lives--;

        if (lives <= 0) {
            Sound.play("res/sounds/explosion.wav", 0);
            dies();
        }
        Sound.play("res/sounds/hit.wav", 0);
    }

    @Override
    public void setLives(int lives){
        this.lives = lives;
    }

    /**
     * Makes the dragon shoot
     */
    private void shoot(){
        logger.debug(this + "Has fired");
        FireBall fireBall = new FireBall(xPoss, yPoss, 33, 11, -1, 0);
        Collisions.getInstance().addDragonBullets(fireBall);
    }

    /**
     * Kills the Dragonsas
     */
    private void dies(){
        new BulletExplosion(xPoss, yPoss, dragonWidth, dragonHeight);
        destroy();
    }

    /**
     * Show the information of the dragon
     */
    private void pressed() {

    }

    /**
     * Move the dragon, updating the coordinates
     */
    private void move() {
        xPoss -= xSpeed;
    }

    private Boolean canShoot() {
        Boolean result = false;
        long time = clock.getTime();
        if (time - lastTime > fire_rate) {
            result = true;
            lastTime = time;
        }
        return result;
    }

    /** Getters and Setters **/

    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public int getParentAge () {
        return parentAge;
    }

    public void setParentAge ( int parentAge){
        this.parentAge = parentAge;
    }

    public int getLives () {
        return lives;
    }

    public int getFire_rate () {
        return fire_rate;
    }

    public void setFire_rate ( int fire_rate){
        this.fire_rate = fire_rate;
    }

    public int getAge () {
        return age;
    }

    public void setAge ( int age){
        this.age = age;
    }

    public String getRank () {
        return rank;
    }

    public void setRank (String rank){
        this.rank = rank;
    }

    public double getxPoss() {
        return xPoss;
    }

    public void setxPoss ( double xPoss){
        this.xPoss = xPoss;
    }

    public double getyPoss () {
        return yPoss;
    }

    public void setyPoss ( double yPoss){
        this.yPoss = yPoss;
    }
}
