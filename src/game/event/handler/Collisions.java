package game.event.handler;

import game.draw.Sprite;
import game.entities.Dragon;
import game.entities.Entity;
import game.logic.lists.SimpleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Collisions {

    private static Logger logger = LoggerFactory.getLogger(Collisions.class);
    private static final Marker COLLISION = MarkerFactory.getMarker("COLLISION");
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
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
            Entity groupEntity = group.getByIndex(i).getValue();
            Sprite groupSprite = groupEntity.getSprite();
            if(sprite.getSprite().getBoundsInParent().intersects(groupSprite.getSprite().getBoundsInParent())){
                collide = true;
                logger.info(COLLISION, entity.toString() + " collide with " + groupEntity.toString());
                if (destroy){
                    if (groupEntity.getClass() == Dragon.class){
                        groupEntity.setLives(0);
                    }
                    groupEntity.hit();
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
            Entity group1Entity = group1.getByIndex(i).getValue();
            for (int j = 0; j < group2.getLarge(); j++) {
                Entity group2Entity = group2.getByIndex(j).getValue();
                Sprite sprite1 = group1Entity.getSprite();
                Sprite sprite2 = group2Entity.getSprite();
                if (sprite1.getSprite().getBoundsInParent().intersects(sprite2.getSprite().getBoundsInParent())) {
                    collide = true;
                    logger.info(COLLISION, group1Entity.toString() + " collide with " + group2Entity.toString());
                    if (destroyG1){
                        group1Entity.hit();
                    }
                    if (destroyG2){
                        group2Entity.hit();
                    }
                    break;
                }
            }
        }
        return collide;
    }

    public void abort(){
        logger.info(SYS, "Collisions aborted");
        instance = null;
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
