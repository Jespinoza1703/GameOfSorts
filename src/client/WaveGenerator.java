package client;

import game.draw.Drawer;
import game.entities.Dragon;

import java.util.List;

public class WaveGenerator {

    public static void listWave(Wave wave){
        List<Dragon> dragons = wave.getDragonsList();
        int waveSize = wave.getSize();
        int columns = 8;
        int rows = waveSize / columns;
        double width = Drawer.width / 2;
        double height = Drawer.height;
        double xOffset = width / columns;
        double yOffset = height / rows;
        double xPoss = Drawer.width;

        int n = 0;
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                double x = (i + 1) * xOffset;
                double y = (j + 1) * yOffset;
                x += xPoss;
                generateDragon(x, y, dragons.get(n));
                n++;
            }
        }
    }

    private static void generateDragon(double x, double y, Dragon dragon){
        int parentAge = dragon.getParentAge();
        int age = dragon.getAge();
        String rank = dragon.getRank();
        String name = dragon.getName();
        int lives = dragon.getLives();
        int fire_rate = dragon.getFire_rate();
        new Dragon(x, y, parentAge, age, rank, name, lives, fire_rate);
    }
}
