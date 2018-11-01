package EntitiesTest;

import game.entities.Dragon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DragonTest {

    @Test
    void ConstructorsTest(){
        Dragon d1 = new Dragon();
        Assertions.assertNotNull(d1);

    }

    @Test
    void GettersAndSettersTest(){
        Dragon d1 = new Dragon();
       /*
        d1.setRank("Commander");
        Assertions.assertEquals("Commander", d1.getRank());
        */
        d1.setAge(122);
        Assertions.assertEquals(122, d1.getAge());

        d1.setLives(2);
        Assertions.assertEquals(2, d1.getLives());

        /*d1.setPoss(100, 100);
        Assertions.assertEquals(100, d1.getxPoss());
        Assertions.assertEquals(100, d1.getyPoss());
*/
        d1.setFire_rate(1000);
        Assertions.assertEquals(1000, d1.getFire_rate());

        d1.setName("Fabian");
        Assertions.assertEquals("Fabian", d1.getName());

        d1.setParentAge(1000);
        Assertions.assertEquals(1000, d1.getParentAge());

        d1.setxPoss(100);
        Assertions.assertEquals(100, d1.getxPoss());

        d1.setyPoss(100);
        Assertions.assertEquals(100, d1.getyPoss());
    }



}
