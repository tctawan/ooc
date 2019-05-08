package ooc.hw1;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Sandbox {
    public static void main(String[] args) {
        String filePath = "/Users/tctawan/Downloads/slow-spring-alert.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        // create clip reference

    }
}
