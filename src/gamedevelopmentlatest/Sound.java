package gamedevelopmentlatest;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
// creating new thread for every sound because when i tried to just play sound in main thread
// i had laggy sound and sometimes and delayed a lot so.....
public class Sound implements Runnable {

    public static int WAITING_TIME = 0;

    Thread thread;

    private boolean playSound = false;
    private String path;

    public Sound(String path) {
        thread = new Thread(this);
        this.path = path;
    }

    @Override
    public void run() {
        // constantly running with same delays i calculate in GameScreen
        // playSound sets to true if i want to play sound
        while (true) {
            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (playSound) {
//				System.out.println("played " + path);
                getSound(path).start();
                playSound = false;
            }
        }
    }

    public static Clip getSound(String path) {
        File file = new File(path);
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            // same situation why i have ClassLoader here
            if (file.exists()) {
                clip.open(AudioSystem.getAudioInputStream(file));
            } else {
                path = path.substring(path.indexOf("/") + 1);
                clip.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemClassLoader().getResource(path)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public void startThread() {
        thread.start();
    }

    public void play() {
        playSound = true;
    }

}
