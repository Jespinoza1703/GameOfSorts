package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;

public class Background extends Entity {

    private double speed;
    private double xPoss = Drawer.width;
    private double yPoss = Drawer.height / 2;
    private String url;
    private Sprite sprite;
    private boolean bounderies = true;

    public Background(double speed, String url){
        this.speed = speed;
        this.url = url;
        this.sprite = loadImages(url);
        Drawer.getInstance().addDrawAtBegining(this);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public Sprite draw() {
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    @Override
    public void update() {
        //xPoss += Math.max(speed, player.getxSpeed());
        xPoss -= speed;
        checkBoundaries();
    }

    @Override
    public void destroy() {

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

    private Sprite loadImages(String url){
        Sprite sprite = new Sprite(xPoss, yPoss, Drawer.width, Drawer.height, url);
        return sprite;
    }

    private void checkBoundaries(){
        var width = Drawer.width;
        var spriteHW = sprite.getWidth() / 2;
        if (xPoss + spriteHW < width && bounderies){
            new Background(speed, url);
            bounderies = false;
        }
        if (xPoss + spriteHW < 0) destroy();
    }
}
