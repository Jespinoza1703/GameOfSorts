package game.event.handler.inputs;

import game.draw.Sprite;
import game.entities.Entity;
import game.entities.Player;
import game.logic.lists.SimpleList;

public class Collisions {
    private static Collisions instance;
    private SimpleList<Entity> dragons = new SimpleList<>();
    private SimpleList<Entity> playerBullets = new SimpleList<>();
    private SimpleList<Entity> dragonBullets = new SimpleList<>();

    public static Collisions getInstance() {
        if (instance == null){
            instance = new Collisions();
        }
        return instance;
    }
    /**
     * Handles collisions of entity vs group
     * @param entity instance of entity
     * @param group list of entities
     * @param destroy destroys an instance in the group
     * @return boolean
     */
    public Boolean collide(Entity entity, SimpleList<Entity> group, boolean destroy){
        Boolean collide = false;
        Sprite sprite = entity.getSprite();
        double x = sprite.getxPoss();
        double y =sprite.getyPoss();
        for (int i = 0; i < group.getLarge(); i++){
            Sprite groupPoss = group.getByIndex(i).getValue().getSprite();
            if(x + sprite.getWidth() > groupPoss.getxPoss() && y == groupPoss.getyPoss()){
                collide = true;
                System.out.println("col");
            }
        }
        return collide;
    }

    /** Handles collisions of entities vs bullets and bullets vs bullets
     * @param group1 instance of
     * @param group2
     * @param destroyG1
     * @param destroy1G2
     * @return
     */
    public Boolean collide(SimpleList<Entity> group1, SimpleList<Entity> group2, boolean destroyG1, boolean destroy1G2){
        Boolean collide = false;
        return collide;
    }

    public void addDragon(Entity dragon){
        dragons.addAtEnd(dragon);
    }

    public void addPlayerBullets(Entity bullet){
        playerBullets.addAtEnd(bullet);
    }

    public void addDragonBullets(Entity bullet){
        dragonBullets.addAtEnd(bullet);
    }


    public SimpleList getDragons() {
        return dragons;
    }

    public SimpleList getPlayerBullets() {
        return playerBullets;
    }

    public SimpleList getDragonBullets() {
        return dragonBullets;
    }
}
