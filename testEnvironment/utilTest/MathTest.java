package utilTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Math;

public class MathTest {
    @Test
    void getRandomNumberTest(){
        int i = Math.getRandomNumberInRange(1, 9);
        Assertions.assertTrue(i>=1);
        Assertions.assertTrue(i<=9);
    }

    @Test
    void clampTest(){
        int i = Math.clamp(1,2,3);
        Assertions.assertEquals(2, i);
        i = Math.clamp(4,2,3);
        Assertions.assertEquals(3, i);
        i = Math.clamp(4,2,5);
        Assertions.assertEquals(4, i);
    }


    @Test
    void approachTest(){
        int i = Math.approach(10, 50, 25);
        Assertions.assertEquals(35, i);

        i = Math.approach(50, 10, 25);
        Assertions.assertEquals(25, i);

        i = Math.approach(50, 10, 45);
        Assertions.assertEquals(10, i);

        i = Math.approach(10, 20, 25);
        Assertions.assertEquals(20, i);
    }






}
