package SoundTest;

import graphics.sound.Sound;
import org.junit.jupiter.api.Test;

public class soundTest {
    @Test
    void testPlay(){
        Sound.play("res/sounds/shot.wav", 0);
    }
    @Test
    void startSongTest(){
        Sound.startSong();
    }
}
