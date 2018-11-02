package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
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
        if (currentSprite == movementAnimation.size()){
            destroy();
        }
        return sprite;
    }

    @Override
    public void update() {

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
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin2.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin3.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin1.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin2.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin3.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin1.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin2.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin3.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin1.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin2.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin3.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin1.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin2.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin3.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin1.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin2.png"));
        movementAnimation.add(sprite = new Sprite(xPoss, yPoss, width, height, root + "deadGriffin3.png"));
        return movementAnimation.get(0);
    }
}
