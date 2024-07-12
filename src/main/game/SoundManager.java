package main.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {

    public void playSound(File soundFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioIn.getFormat();
            System.out.println("Audio Format: " + format);

            // Check if the format is supported directly
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                // If not, try to convert it to a supported format
                AudioFormat pcmFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        16,
                        format.getChannels(),
                        format.getChannels() * 2,
                        format.getSampleRate(),
                        false);
                audioIn = AudioSystem.getAudioInputStream(pcmFormat, audioIn);
                format = pcmFormat;
                System.out.println("Converted Audio Format: " + format);
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

            // Using a separate thread to wait for the sound to finish
            new Thread(() -> {
                try {
                    Thread.sleep(clip.getMicrosecondLength() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    clip.close();
                }
            }).start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to play footstep sound
    public void playFootstepSound() {
        playSound(new File("src/main/sounds/footsteps-sound.wav"));
    }

    // Method to play goblin sound
    public void playGoblinSound() {
        playSound(new File("src/main/sounds/goblin-sound.wav"));
    }

    // Method to play goblin death sound
    public void playGoblinDeathSound() {
        playSound(new File("src/main/sounds/goblin-death.wav"));
    }

    // Method to play troll sound
    public void playTrollSound() {
        playSound(new File("src/main/sounds/troll-sound.wav"));
    }

    // Method to play troll death sound
    public void playTrollDeathSound() {
        playSound(new File("src/main/sounds/troll-dying1.wav"));
    }

    // Method to play sword sound
    public void playSwordSound() {
        playSound(new File("src/main/sounds/sword-sound.wav"));
    }
}
