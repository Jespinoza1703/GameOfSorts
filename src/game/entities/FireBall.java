package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.inputs.Collisions;
import game.event.handler.inputs.KeyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Clock;

import java.util.ArrayList;

public class FireBall extends Entity {

    private KeyReader key = KeyReader.getInstance();
    private Clock clock = Clock.getInstance();
    private Sprite sprite;
    private double xPoss, yPoss;
    private int xDir, yDir;
    private int xSpeed = 10, ySpeed = 10;
    private double fireWidth, fireHeight;
    private ArrayList<Sprite> animations = new ArrayList<>();
    private ArrayList<Sprite> deathAnimations = new ArrayList<>();
    private int animationTimer = 100;
    private double lastAnimationTime = 0;
    private int currentSprite = 0;
    private static Logger logger = LoggerFactory.getLogger("FireBall");

    /**
     * Constructor
     * @param xPoss
     * @param yPoss
     * @param fireWidth
     * @param fireHeight
     * @param xDir
     * @param yDir
     */
    public FireBall(double xPoss, double yPoss, double fireWidth, double fireHeight, int xDir, int yDir){
        logger.debug("Created New FireBall");
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

    /**
     * Draws the fireball
     * @return
     */
    @Override
    public Sprite draw() {
        logger.debug("Draw" + this);
        long time = clock.getTime();
        if (time - lastAnimationTime > animationTimer){
            sprite = animations.get(currentSprite);
            lastAnimationTime = time;
            currentSprite = (currentSprite + 1) % animations.size();
        }
        sprite.move(xPoss, yPoss);
        sprite.getSprite().setScaleX(-xDir);
        return sprite;
    }

    /**
     * Simulate the movement of the fireball
     * @return
     */
    private Sprite loadImages(){
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball1.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball2.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball3.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball4.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/fireball/Fireball5.png"));
        deathAnimations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight,
                "file:res/img/entities/dragon/fDeath1"));
        return animations.get(0);

    }

    @Override
    /**
     *Detects coalitions
     */
    public void hit(){
        logger.debug(this + " has been hit");
        dies();
    }

    @Override
    public void setLives(int lives) {

    }

    /**
     * Kill the fireball
     */
    private void dies(){
        logger.debug(this + " has been killed");
        new BulletExplosion(xPoss, yPoss, fireHeight*2, fireHeight*2);
        destroy();
    }

    /**
     * Update the state of the fireball Image
     */
    @Override
    public void update() {
        moveFire();
    }

    /**
     * Destroys the fireball
     */
    @Override
    public void destroy() {
        logger.debug(this+ " has been Destroyed");
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
        Collisions.getInstance().deleteBullets(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Update the position of the fireball
     */
    public void moveFire(){
        xPoss += xSpeed * xDir;
        yPoss += ySpeed * yDir;

    }

    /**Getters and Setters**/

    public double getxPoss() {
        return xPoss;
    }

    public double getyPoss() {
        return yPoss;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setxPoss(double xPoss) {
        this.xPoss = xPoss;
    }

    public void setyPoss(double yPoss) {
        this.yPoss = yPoss;
    }

    public int getxDir() {
        return xDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    public double getFireWidth() {
        return fireWidth;
    }

    public void setFireWidth(double fireWidth) {
        this.fireWidth = fireWidth;
    }

    public double getFireHeight() {
        return fireHeight;
    }

    public void setFireHeight(double fireHeight) {
        this.fireHeight = fireHeight;
    }
}
