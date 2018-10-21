package game.draw;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private int width;
    private int heigth;
    private int xPoss;
    private int yPoss;
    private ImageView sprite;


    public Sprite(int xPoss, int yPoss, String url) {
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        Image img = new Image(url);
        ImageView imgView = new ImageView(img);
        imgView.setX(xPoss);
        imgView.setY(yPoss);
        width = (int) imgView.getFitWidth();
        heigth = (int) imgView.getFitHeight();
        sprite = imgView;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth(){
        return heigth;
    }

    public int getxPoss(){ return xPoss; }

    public int getyPoss(){return yPoss; }

    public ImageView getSprite() { return sprite; }
}
