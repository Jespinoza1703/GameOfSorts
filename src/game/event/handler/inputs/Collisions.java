package game.event.handler.inputs;

import game.draw.Sprite;
import game.entities.Dragon;
import game.entities.Entity;
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
        for (int i = 0; i < group.getLarge(); i++){
            Sprite groupSprite = group.getByIndex(i).getValue().getSprite();
            if(sprite.getSprite().getBoundsInParent().intersects(groupSprite.getSprite().getBoundsInParent())){
                collide = true;
                if (destroy){
                    Entity entity1 = group.getByIndex(i).getValue();
                    if (entity1.getClass() == Dragon.class){
                        entity1.setLives(0);
                    }
                    entity1.hit();
                }
                break;
            }
        }
        return collide;
    }

    /** Handles collisions of entities vs bullets and bullets vs bullets
     * @param group1 list of entities
     * @param group2 list of entities
     * @param destroyG1 destroys an instance in group1
     * @param destroyG2 destroyes an instance  in group2
     * @return boolean
     */
    public Boolean collide(SimpleList<Entity> group1, SimpleList<Entity> group2, boolean destroyG1, boolean destroyG2) {
        Boolean collide = false;
        for (int i = 0; i < group1.getLarge(); i++) {
            Sprite group1Poss = group1.getByIndex(i).getValue().getSprite();
            for (int j = 0; j < group2.getLarge(); j++) {
                Sprite group2Poss = group2.getByIndex(j).getValue().getSprite();
                if (group1Poss.getSprite().getBoundsInParent().intersects(group2Poss.getSprite().getBoundsInParent())) {
                    collide = true;
                    if (destroyG1){
                        group1.getByIndex(i).getValue().hit();
                    }
                    if (destroyG2){
                        group2.getByIndex(j).getValue().hit();
                    }
                    break;
                }
            }
        }
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

    public void deleteDragon(Entity draw){
        dragons.delete(dragons.searchIndex(draw));
    }

    public void deleteBullets(Entity draw){
        if(playerBullets.searchIndex(draw) != -1){
            playerBullets.delete(playerBullets.searchIndex(draw));
        }
        else if (dragonBullets.searchIndex(draw) != -1) {
            dragonBullets.delete(dragonBullets.searchIndex(draw));
        }
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
