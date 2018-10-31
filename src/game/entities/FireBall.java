package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.Collisions;
import game.event.handler.inputs.KeyReader;
import graphics.sound.Sound;
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
    private int xSpeed = 15, ySpeed = 8;
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
     * @param xDir
     * @param yDir
     */
    public FireBall(double xPoss, double yPoss, double fireWidth, int xDir, int yDir){
        logger.debug("Created New FireBall");
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.fireWidth = fireWidth;
        this.fireHeight = fireWidth * 0.4;
        this.xDir = xDir;
        this.yDir = yDir;
        sprite = loadImages();
        Drawer.getInstance().addDrawAtBeginning(this);
        GameController.getInstance().addEntity(this);
        Sound.play("res/sounds/shot.wav", 0);
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
        String[] colors = {"blue", "green", "purple", "red", "yellow"};
        // int i = Math.getRandomNumberInRange(0, colors.length - 1);
        String color = colors[3];
        if(xDir > 0) color = colors[4];

        String root = "file:res/img/entities/fireballs/" + color;
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight, root + "/1.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight, root + "/2.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight, root + "/3.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight, root + "/4.png"));
        animations.add(sprite = new Sprite(xPoss, yPoss, fireWidth, fireHeight, root + "/5.png"));
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

        var width = Drawer.width;
        var height = Drawer.height;
        if (yPoss < 0) destroy();
        if (yPoss > height) destroy();
        if (xPoss < 0) destroy();
        if (xPoss > width) destroy();
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
