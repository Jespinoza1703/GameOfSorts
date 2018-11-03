package game.draw;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private double width;
    private double height;
    private double xPoss;
    private double yPoss;
    private ImageView sprite;
    public Effect effect = new ColorAdjust(1, 0, 1, 0);

    /**
     * Class constructor
     * @param xPoss horizontal position
     * @param yPoss vertical position
     * @param width image width
     * @param height image height
     * @param url corresponds to the images path
     */
    public Sprite(double xPoss, double yPoss, double width, double height, String url) {
        Image img = new Image(url);
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(width);
        imgView.setFitHeight(height);
        imgView.setX(xPoss - width / 2);
        imgView.setY(yPoss - height / 2);
        this.xPoss = imgView.getX();
        this.yPoss = imgView.getY();
        this.width = imgView.getFitWidth();
        this.height = imgView.getFitHeight();
        sprite = imgView;
    }

    /**
     * Places the image on the screen and updates its position
     * @param xPoss
     * @param yPoss
     */
    public void move(double xPoss, double yPoss){
        sprite.setX(xPoss - width / 2);
        sprite.setY(yPoss - height / 2);
        this.xPoss = sprite.getX();
        this.yPoss = sprite.getY();
    }

    /**
     * Getters and setters
     * @return
     */
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public ImageView getSprite() { return sprite; }
}
