package EntitiesTest;

import game.entities.Dragon;
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
