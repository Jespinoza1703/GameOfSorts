package game.entities;

import game.logic.lists.Node;
import game.logic.lists.SimpleList;
import game.logic.sorts.SortMethods;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static SimpleList dragonsList = new SimpleList(); //Contains the alive dragons list
    private static AVLTree dragonOrganization = new AVLTree();
    private static BinaryTree BinaryDragon = new BinaryTree();
    private static SimpleList DragonListSorted = new SimpleList();

    private static Logger logger = LoggerFactory.getLogger("Dragon");




    public Dragon () {

        logger.debug("Created new Dragon");
        int lives = ((int)((Math.random())*100));
        this.name = (NameGenerator.generateName());
        logger.debug(this + " Name: " + this.name);
        this.fire_rate = ((int)((Math.random())*100));
        logger.debug(this + " Fire Rate: " + this.fire_rate);
        this.age = ((int)((Math.random())*1000));
        logger.debug(this + " Age: " + this.age);
        if (lives <= 33){
            this.setLives(1);
            logger.debug(this + " Lives: " + this.lives);
        }else if(lives <= 66){
            this.setLives(2);
            logger.debug(this + " Lives: " + this.lives);
        }else if(lives < 100){
            this.setLives(3);
            logger.debug(this + " Lives: " + this.lives);
        }
        dragonsList.addDragon(this);



    }

    public Dragon(Dragon parent, int lives, int age, String rank) {
        logger.debug("Created new Dragon");
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
        logger.info(this + " has been hit it ");

    }

    private void shoot(){
        logger.debug(this + " has shoot");
    }

    public void dies(){
        logger.info(this + " has been eliminated");
        int i = 0;
        while (dragonsList.getByIndex(i).getDragon() != this && dragonsList.getByIndex(i).getNext() != null){
            i++;
        }
        dragonsList.delete(i);
    }

    private void pressed(){
        logger.debug(this + "has been clicked");
    }





    private static SimpleList getSortedListByAge(){
        logger.debug("Change Dragons organization to Sorted by Age");
        updateSortedList();
        SortMethods.selectionSort(DragonListSorted);
        return DragonListSorted;

}

    private static SimpleList getSortListByFireRate(){
        logger.debug("Change Dragons organization to Sorted by Fire Rate");
        updateSortedList();
        SortMethods.insertionSort(DragonListSorted);
        return DragonListSorted;
    }

    private static void updateSortedList(){
        logger.debug("Update Simple List of dragons that will be sorted");
        Node temp = dragonsList.getFirst();
        DragonListSorted.clearOut();
        while (temp != null){
            DragonListSorted.addAtEnd(temp.getDragon());
            temp =  temp.getNext();
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

    public int getxPoss() {
        return xPoss;
    }

    public void setxPoss(int xPoss) {
        this.xPoss = xPoss;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public static SimpleList getDragonsList() {
        return dragonsList;
    }

    public static void setDragonsList(SimpleList dragonsList) {
        Dragon.dragonsList = dragonsList;
    }

    public static AVLTree getDragonOrganization() {
        return dragonOrganization;
    }

    public static void setDragonOrganization(AVLTree dragonOrganization) {
        Dragon.dragonOrganization = dragonOrganization;
    }

    public static BinaryTree getBinaryDragon() {
        return BinaryDragon;
    }

    public static void setBinaryDragon(BinaryTree binaryDragon) {
        BinaryDragon = binaryDragon;
    }

    public static SimpleList getDragonListSorted() {
        return DragonListSorted;
    }

    public static void setDragonListSorted(SimpleList dragonListSorted) {
        DragonListSorted = dragonListSorted;
    }
}
