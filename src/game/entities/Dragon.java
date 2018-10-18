package game.entities;

import game.logic.lists.Node;
import game.logic.lists.SimpleList;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;
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
    private static SimpleList dragonsList = new SimpleList(); //Contains the alive dragons list
    private static AVLTree dragonOrganization = new AVLTree();
    private static BinaryTree BinaryDragon;
    private static SimpleList OrganizationList;



    public Dragon () {

        this.name = (NameGenerator.generateName());
        this.fire_rate = ((int)((Math.random())*100));
        this.age = ((int)((Math.random())*1000));

        dragonsList.addDragon(this);
        this.updateOrganization();


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

    public void dies(){
        int i = 0;
        while (dragonsList.getByIndex(i).getDragon() != this && dragonsList.getByIndex(i).getNext() != null){
            i++;
        }
        dragonsList.delete(i);
        updateOrganization();
    }

    private void pressed(){

    }

    private void updateOrganization(){
        OrganizationList.clearOut();
        this.updateTreesOrganization();
        OrganizationList.addAtEnd(BinaryDragon);
        OrganizationList.addAtEnd(dragonOrganization);

    }

    private void updateTreesOrganization(){
        Node tmp = dragonsList.getFirst();
        while (tmp != null){
            dragonOrganization.insertDragon(this.age, this);
            BinaryDragon.addDragon(this.age, this);
            tmp = tmp.getNext();
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

    public static SimpleList getOrganizationList() {
        return OrganizationList;
    }

    public static void setOrganizationList(SimpleList organizationList) {
        OrganizationList = organizationList;
    }
}
