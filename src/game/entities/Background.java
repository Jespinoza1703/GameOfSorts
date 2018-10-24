package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;

public class Background extends Entity {

    public static Player player;
    private int level; // [1, 3]
    private double speed;
    private double xPoss;
    private double yPoss;
    private double width;
    private double height;
    private String url;
    private Sprite sprite;
    private boolean boundaries = true;

    public Background(double speed, double xPoss, double width, double height, String url , int level){
        this.level = level;
        this.speed = speed;
        this.xPoss = xPoss;
        this.width = width;
        this.height = height;
        this.url = url;
        this.sprite = loadImages(url);
        yPoss = Drawer.height - sprite.getHeight() / 2;
        Drawer.getInstance().addBackGround(this, level);
        GameController.getInstance().addEntity(this);
    }

    @Override
    public Sprite draw() {
        sprite.move(xPoss, yPoss);
        return sprite;
    }

    @Override
    public void update() {
        xPoss -= Math.max(speed, player.getxSpeed());
        //xPoss -= speed;
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
        Sprite sprite = new Sprite(xPoss, yPoss, Drawer.width, height, url);
        return sprite;
    }

    private void checkBoundaries(){
        var spriteHW = sprite.getWidth() / 2;
        if (xPoss < 2000 && boundaries){
            var poss = xPoss + (spriteHW * 2);
            new Background(speed, poss, width, height, url , level);
            boundaries = false;
        }
        if (xPoss + spriteHW < 0) destroy();
    }
}
