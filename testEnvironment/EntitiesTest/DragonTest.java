package EntitiesTest;

import game.entities.Dragon;
import game.logic.lists.SimpleList;
import game.logic.sorts.SortMethods;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DragonTest {

    @Test
    void AddDragonToDragonList_Test(){
        new Dragon();
        Dragon dragon = Dragon.getDragonsList().getFirst().getDragon();
        assertNotNull(dragon);

    }

   @Test
   void SortDragonsByFireRate(){
       int i = 0;
       while (i < 3){
           new Dragon();
           i++;
       }
       SimpleList dragons = Dragon.getDragonsList();
       SortMethods.insertionSort(dragons);
       boolean result = dragons.getByIndex(0).getDragon().getFire_rate() <= dragons.getByIndex(0).getDragon().getFire_rate();
       assertEquals(true, result);
    }



}
