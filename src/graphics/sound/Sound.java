package graphics.sound;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Sound extends Thread{

    private Clip clip;
    private static int loops = 0;
    public static Thread audio;

    private Sound(String fileName) {
        //El try primero crea un File el cual ya sabe que tiene que ser una ruta String y los catch son todos los posibles casos de error. Tiene que ser wav
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // Esta vara carga el archivo como un clip de memoria
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                System.out.println("Sound: El archivo deseado no fue encontrado " + fileName);
            }
        }
        catch (MalformedURLException e) {
            //e.printStackTrace();
            System.out.println("Sound: URL corrupto: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            //e.printStackTrace();
            System.out.println("Sound: El tipo de archivo es invalido o no soportado: " + e);
        }
        catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Sound: error de entrada o salida en el sonido: " + e);
        }
        catch (LineUnavailableException e) {
            //e.printStackTrace();
            System.out.println("Sound: Linea no valida: " + e);
        }

        // Metodo para reproducir normal, pra reproducir en loop y para pausar la musica
    }

    /**
     * Reproduces a sound file
     * @param file direction of the file in resources/sounds/..
     */
    public static void play(String file, int loops){
        audio = new Sound(file);
        Sound.loops = loops;
        audio.start();
    }

    @Override
    public void run() {
        while (true){
            clip.loop(loops);
        }
    }

    private Sound(){
        try {
            File file = new File("res/sounds/ocarina.wav");
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // Esta vara carga el archivo como un clip de memoria
                clip = AudioSystem.getClip();
                clip.open(sound);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-5.0f); // Reduce volume by 10 decibels.
            }
            else {
                System.out.println("Sound: El archivo deseado no fue encontrado " + "res/sounds/ocarina.wav");
            }
        }
        catch (MalformedURLException e) {
            //e.printStackTrace();
            System.out.println("Sound: URL corrupto: " + e);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void startSong(){
        audio = new Sound();
        Sound.loops = 5;
        audio.start();
    }
}