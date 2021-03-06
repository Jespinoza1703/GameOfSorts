package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import javafx.scene.image.ImageView;
import util.Clock;

import java.util.ArrayList;

public class PlayerDeath extends Entity {

    private Clock clock = Clock.getInstance();
    private double xPoss;
    private double yPoss;
    private double width;
    private double height;
    private Sprite sprite;
    private ArrayList<Sprite> movementAnimation = new ArrayList<>();
    private double animationTimer = 50;
    private double lastAnimationTime = 0;
    private int currentSprite = 0;
    private int ySpeed = 3;
    private double rotate = 0;

    public PlayerDeath(double xPoss, double yPoss, double width, double height) {
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.width = width;
        this.height = height;
        sprite = loadImages();
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public Sprite draw() {
        long time = clock.getTime();
        if (time - lastAnimationTime > animationTimer){
            sprite = movementAnimation.get(currentSprite);
            lastAnimationTime = time;
            currentSprite = (currentSprite + 1) % movementAnimation.size();
        }
        sprite.getSprite().setRotate(rotate);
        rotate -= 10;
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    /**
     * Update the position of the player
     */
    public void move(){
        yPoss += ySpeed;

        var drawerHeight = Drawer.height;
        if (yPoss < 0){
            destroy();
        }
        if (yPoss > drawerHeight - height) destroy();
    }
    @Override
    public void update() {
        move();
    }

    @Override
    public void destroy() {
        GameController.getInstance().setGameEnd(true);
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void hit() {
    }

    @Override
    public void setLives(int lives) {

    }

    /**
     * Loads images for entity PlayerDeath
     * @return Sprite
 0    */
    private Sprite loadImages(){
        String root = "file:res/img/entities/griffin/deadGriffin/";
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin1.png"));
        return movementAnimation.get(0);
    }
}
