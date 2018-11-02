package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;

public class PlayerDash extends Entity {

    private double xPoss;
    private double yPoss;
    private Sprite sprite;
    private int delay = 10;

    public PlayerDash(double x, double y, Sprite img){
        xPoss = x;
        yPoss = y;
        sprite = img;
        Drawer.getInstance().addDrawAtBeginning(this);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public Sprite draw() {
        sprite.getSprite().setEffect(sprite.effect);
        return sprite;
    }

    @Override
    public void update() {
        delay--;
        if (delay <= 0) destroy();
    }

    @Override
    public void destroy() {
        Drawer.getInstance().deleteEntity(this);
        GameController.getInstance().deleteEntity(this);
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public void hit() {

    }

    @Override
    public void setLives(int lives) {

    }
}
