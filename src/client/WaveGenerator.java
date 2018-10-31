package client;

import game.draw.Drawer;
import game.entities.Dragon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import util.Math;

import java.util.List;

public class WaveGenerator {

    private static Logger logger = LoggerFactory.getLogger(WaveGenerator.class);
    private static final Marker SYS = MarkerFactory.getMarker("SYS");
    private static String[] waveSortMethod = {"selection", "insertion", "quick", "binary-tree", "avl-tree"};

    public static Wave getNewWave(int size) {
        Wave wave = new Wave(0, size);
        unSortedWave(wave);
        return wave;
    }

    public static Wave getWaveSorted(Wave wave, int type) {
        int i = type % waveSortMethod.length;
        String sort = waveSortMethod[i];

        // Here the client ask for a sorted wave

        // Reposition the Dragons in formation
        if (i < 3) sort = "list";
        wave.formation = sort;
        listWave(wave);
        return wave;
    }

    private static void unSortedWave(Wave wave){
        List<Dragon> dragons = wave.getDragonsList();
        int waveSize = wave.getSize();
        int columns = 8;
        int rows = waveSize / columns;
        double width = Drawer.width / 2;
        double height = Drawer.height;
        double xOffset = width / columns;
        double yOffset = height / (rows + 1);
        double xPoss = Drawer.width;
        double yMax = yOffset * rows;

        int n = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                double x = (i + 1) * xOffset;
                double y = Math.getRandomNumberInRange(0, (int) yMax);
                x += xPoss;
                generateDragon(x, y, dragons, n);
                n++;
            }
        }
    }

    private static void listWave(Wave wave) {
        List<Dragon> dragons = wave.getDragonsList();
        int waveSize = wave.getSize();
        int columns = (int) java.lang.Math.sqrt(waveSize);
        int rows = waveSize / columns;
        double width = Drawer.width / 2;
        double height = Drawer.height;
        double xOffset = width / columns;
        double yOffset = height / (rows + 1);
        double xPoss = Drawer.width;

        int n = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                double x = (i + 1) * xOffset;
                double y = (j + 1) * yOffset;
                x += xPoss;
                Dragon dragon = dragons.get(n);
                //dragon.setxPoss(x);
                dragon.setyPoss(y);
                n++;
            }
        }
        while(n < dragons.size()){
            columns += 1;
            for (int j = 0; j < rows; j++) {
                double y = (j + 1) * yOffset;
                Dragon dragon = dragons.get(n);
                dragon.setyPoss(y);
            }
            n++;
        }

    }

    private static void treeWave(Wave wave){

    }

    private static void generateDragon(double x, double y, List<Dragon> list, int i) {
        Dragon dragon = list.get(i);
        int parentAge = dragon.getParentAge();
        int age = dragon.getAge();
        String rank = dragon.getRank();
        String name = dragon.getName();
        int lives = dragon.getLives();
        int fire_rate = dragon.getFire_rate();
        list.set(i, new Dragon(x, y, parentAge, age, rank, name, lives, fire_rate));
    }
}
