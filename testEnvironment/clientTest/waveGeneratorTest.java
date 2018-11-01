package clientTest;


import client.Wave;
import client.WaveGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class waveGeneratorTest {

    @Test
    void getNewWaveTest(){
        Wave wave = WaveGenerator.getNewWave(5);
        Assertions.assertNotNull(wave);
    }

    @Test
    void getWaveSort(){
        Wave wave = WaveGenerator.getNewWave(5);
        WaveGenerator.getWaveSorted(wave, 0);
        Assertions.assertEquals("selection", wave.formation);
        WaveGenerator.getWaveSorted(wave, 1);
        Assertions.assertEquals("insertion", wave.formation);
        WaveGenerator.getWaveSorted(wave, 2);
        Assertions.assertEquals("quick", wave.formation);
        WaveGenerator.getWaveSorted(wave, 4);
        Assertions.assertEquals("avl-tree", wave.formation);
        WaveGenerator.getWaveSorted(wave, 3);
        Assertions.assertEquals("binary-tree", wave.formation);
    }
    @Test
    void  treeWave(){
        Wave wave = WaveGenerator.getNewWave(5);
        WaveGenerator.treeWave(wave);
    }
    /*@Test
    void unsortedWaveTest(){
        WaveGenerator.getNewWave(5);
        WaveGenerator.getNewWave(70);
        WaveGenerator.getNewWave(8);
        WaveGenerator.getNewWave(100);
        WaveGenerator.getNewWave(16);
    }*/


}
