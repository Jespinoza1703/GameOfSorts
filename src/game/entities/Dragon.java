package game.entities;

import game.GameController;
import game.draw.Drawer;
import game.draw.Sprite;
import game.event.handler.Collisions;
import graphics.sound.Sound;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import util.Clock;
import util.Math;
import util.NameGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class Dragon extends Entity {

    private static Logger logger = LoggerFactory.getLogger(Dragon.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private Clock clock = Clock.getInstance();
    private String name = NameGenerator.generateName();
    private int parentAge;
    private int lives = Math.getRandomNumberInRange(1, 3); // [1, 3]
    private int fire_rate = Math.getRandomNumberInRange(400, 2000);  // [200, 1000]
    private int age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry
    private double xPoss, yPoss;
    private double dragonWidth = 80, dragonHeight = 65;
    private double xSpeed = 0.2;
    private long lastTime = 0;
    private Sprite sprite;
    private ArrayList<Sprite> movementAnimations = new ArrayList<>();
    private double animationTimer = 200;
    private double lastAnimationTime = 0;
    private int hitTimer = 0;
    private int currentSprite = 0;
    private boolean animating = false;
    private double xNew;
    private double yNew;

    /**
     * Basic constructor
     */
    public Dragon() {

        logger.debug("Created new dragon");
    }

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
        sprite.getSprite().setOnMousePressed(event -> {
            try {
                pressed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Dragon(int parentAge, int age, String rank) {
        logger.debug("Created new dragon");
        this.parentAge = parentAge;
        this.age = age;
        this.rank = rank;
    }

    public Dragon(double xPoss, double yPoss, int parentAge, int age, String rank, String name, int lives, int fire_rate) {
        this.xPoss = xPoss;
        this.yPoss = yPoss;
        this.parentAge = parentAge;
        this.age = age;
        this.rank = rank;
        this.name = name;
        this.lives = lives;
        this.fire_rate = fire_rate;
        sprite = loadImages();
        Drawer.getInstance().addDrawAtEnd(this);
        GameController.getInstance().addEntity(this);
        Collisions.getInstance().addDragon(this);
        sprite.getSprite().setOnMousePressed(event -> {
            try {
                pressed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Update the dragon position
     */
    @Override
    public void update() {
        if (!animating) {
            move();
            hitAnimation();
            if (canShoot()) {
                shoot();
            }
        }
        else {
            xPoss = (double) Math.approach((int)xPoss, (int)xNew, 10);
            yPoss = (double) Math.approach((int)yPoss, (int)yNew, 10);
            if (xPoss == (int)xNew && yPoss == (int)yNew) animating = false;
        }
    }

    private void hitAnimation() {
        if (hitTimer > 0) {
            hitTimer--;
            sprite.getSprite().setEffect(sprite.effect);
        }
        if (hitTimer == 0) sprite.getSprite().setEffect(null);
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
        String[] colors = {"blue", "green", "purple", "red", "yellow"};
        // int i = Math.getRandomNumberInRange(0, colors.length - 1);
        String color = colors[3];

        if (rank.equals("Commander")) {
            color = colors[4];
            dragonWidth = 150;
            dragonHeight = 120;
        }

        if (rank.equals("Captain")) {
            color = colors[1];
            dragonWidth = 100;
            dragonHeight = 85;
        }

        String root = "file:res/img/entities/dragons/" + color;
        movementAnimations.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight, root + "/fly2.png"));
        movementAnimations.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight, root + "/fly1.png"));
        movementAnimations.add(sprite = new Sprite(xPoss, yPoss, dragonWidth, dragonHeight, root + "/fly3.png"));
        return movementAnimations.get(0);
    }

    /**
     * Update dragon when received a shoot
     */
    @Override
    public void hit() {
        logger.debug(this + "has been hit");
        lives--;
        hitTimer = 40;
        if (lives <= 0) {
            Sound.play("res/sounds/explosion.wav", 0);
            dies();
        }

        Sound.play("res/sounds/hit.wav", 0);
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Makes the dragon shoot
     */
    private void shoot() {
        FireBall fireBall = new FireBall(xPoss, yPoss, sprite.getWidth() / 1.5, -1, 0);
        Collisions.getInstance().addDragonBullets(fireBall);
    }

    /**
     * Kills the Dragonsas
     */
    private void dies() {
        new BulletExplosion(xPoss, yPoss, dragonWidth, dragonHeight);
        destroy();
    }

    /**
     * Show the information of the dragon
     */
    private void pressed() throws IOException {

        //GameController.getInstance().pause();
        Parent root = FXMLLoader.load((getClass().getResource("graphics/layouts/me.fxml")));

        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(root));
        secondStage.show();
        }
    /**
     * Move the dragon, updating the coordinates
     */
    private void move() {
        xPoss -= java.lang.Math.max(xSpeed, GameController.player.getSpeed() + xSpeed);
        xPoss -= xSpeed;

        // Calculates boundaries
        var height = Drawer.height;
        var width = Drawer.width;
        var spriteHH = sprite.getHeight() / 2;
        var spriteHW = sprite.getWidth() / 2;
        if (yPoss - spriteHH < 0) yPoss = 0 + spriteHH;
        if (yPoss + spriteHH > height) yPoss = height - spriteHH;
        if (xPoss + spriteHW > width) lastTime = 0;
        // Gets to the end of the screen
        if (xPoss - spriteHW < 0) {
            xPoss = 0 + spriteHW;
            if (GameController.player.isAlive()) GameController.player.hit();
            dies();
        }
    }

    private Boolean canShoot() {
        Boolean result = false;
        lastTime++;
        if (lastTime > fire_rate) {
            result = true;
            lastTime = 0;
        }
        return result;
    }

    /**
     * Getters and Setters
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentAge() {
        return parentAge;
    }

    public void setParentAge(int parentAge) {
        this.parentAge = parentAge;
    }

    public int getLives() {
        return lives;
    }

    public int getFire_rate() {
        return fire_rate;
    }

    public void setFire_rate(int fire_rate) {
        this.fire_rate = fire_rate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
        movementAnimations = new ArrayList<>();
        loadImages();
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

    public void setPoss(double xPoss, double yPoss){
        animating = true;
        xNew = xPoss;
        yNew = yPoss;
    }
}
