package game.entities;

public class Player {

    private static Player instance;
    private int lives = 3;
    private int ySpeed = 20;
    private int xSpeed = 30;
    private int xposs = 200;
    private int yposs = 200;
    private int fire_rate = 90;
    private int damage = 1;
    private String state = "Moving"; // Moving / Dead / Dashing

    private Player(){

    }

    public static Player getInstance(){
        if (instance == null){
            instance = new Player();
        }
        return instance;
    }

    public void generatePlayer(){

    }

    private void event(){

    }

    private void hit(){

    }

    private void heal(){

    }

    private void shoot(){

    }
}
