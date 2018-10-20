package utilTest;

import util.NameGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameGeneratorTest {

    @Test
    void generateRandomName(){
        String a = NameGenerator.generateName();
        String b = NameGenerator.generateName();
        Boolean result = a == b;
        assertEquals(false, result);
    }

}
