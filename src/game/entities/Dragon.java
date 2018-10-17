package game.entities;

import game.logic.lists.Node;
import game.logic.lists.SimpleList;
import game.logic.trees.AVLTree;
import game.logic.trees.TreeNode;
import javafx.scene.shape.Rectangle;
import util.NameGenerator;

public class Dragon extends Entity {

    private String name;
    private Dragon parent;
    private int lives; // [1, 3]
    private int fire_rate;  // [10, 100]
    private int age;  // [1, 1000]
    private String rank;  // Commander / Captain / Infantry
    private int xPoss;
    private int xSpeed;
    private static SimpleList dragonsList; //Contains the alive dragons list
    private static AVLTree dragonOrganization;


    public Dragon () {

        this.name = (NameGenerator.generateName());
        this.fire_rate = ((int)((Math.random())*100));
        this.age = ((int)((Math.random())*1000));


    }

    public Dragon(Dragon parent, int lives, int age, String rank) {
        this.name = (NameGenerator.generateName());
        this.fire_rate = ((int)((Math.random())*100));
        this.age = age;
        this.parent = parent;
        this.lives = lives;
        this.rank = rank;
    }

    @Override
    public void update(){
        xPoss -= xSpeed;
    }

    @Override
    public Rectangle draw() {
        return null;
    }

    private void hit(){

    }

    private void shoot(){

    }

    private void dies(){

    }

    private void pressed(){

    }

    private void updateOrganization(){
        Node temp = dragonsList.getFirst();
        while (temp.getNext() != null){
            dragonOrganization.insert(temp.getDragon().getAge());
            temp = temp.getNext();
        }
    }
    private void updateOrganization(Dragon dragon){
        dragonsList.addDragon(dragon);
        Node temp = dragonsList.getFirst();
        while (temp.getNext() != null){
            dragonOrganization.insertDragon(temp.getDragon().getAge(), temp.getDragon());
            temp = temp.getNext();
        }
    }


    /** Getters andSetters **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dragon getParent() {
        return parent;
    }

    public void setParent(Dragon parent) {
        this.parent = parent;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
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
    }
}
