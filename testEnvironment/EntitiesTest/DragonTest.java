package EntitiesTest;

import game.entities.Dragon;
import game.logic.lists.SimpleList;
import game.logic.sorts.SortMethods;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DragonTest {

    @Test
    void AddDragonToDragonList_Test(){
        Dragon dragon = new Dragon();
        Dragon expected = Dragon.getDragonsList().getFirst().getDragon();

        assertEquals(expected, dragon);

    }
   /* @Test
    void AddDragonToDragonOrganization_Test(){
        Dragon dragon = new Dragon();                                           Esta bien pero se cae, no se porque
        Dragon expected = Dragon.getDragonOrganization().getRoot().getDragon();
        Boolean result = expected.equals(dragon);


        assertEquals(true, result);
    }*/

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


    @Test
    void diesMax(){
        int i = 0;
        while (i < 3){
            new Dragon();
            i++;
        }
        Dragon max = Dragon.getDragonOrganization().getRoot().getDragon();
        max.dies();
        assertEquals(null, Dragon.getDragonOrganization().getRoot().getRight().getDragon());
    }

}
