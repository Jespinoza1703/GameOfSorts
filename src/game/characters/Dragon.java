package game.characters;

public class Dragon {

    private String name;
    private Dragon parent;
    private int lives; // [1, 3]
    private int fire_rate;  // [10, 100]
    private int age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry

    public Dragon(String name, Dragon parent, int lives, int fire_rate, int age, String rank) {
        this.name = name;
        this.parent = parent;
        this.lives = lives;
        this.fire_rate = fire_rate;
        this.age = age;
        this.rank = rank;
    }

    private void hit(){

    }

    private void shoot(){

    }

    private void dies(){

    }

    private void pressed(){

    }
}
