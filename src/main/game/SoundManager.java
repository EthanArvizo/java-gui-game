package main.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {

    private Map<String, Clip> clips;

    public SoundManager() {
        clips = new HashMap<>();
        loadClip("footstep", "src/main/sounds/footsteps-sound.wav");
        loadClip("goblin", "src/main/sounds/goblin-sound.wav");
        loadClip("goblinDeath", "src/main/sounds/goblin-death.wav");
        loadClip("troll", "src/main/sounds/troll-sound.wav");
        loadClip("trollDeath", "src/main/sounds/troll-dying1.wav");
        loadClip("sword", "src/main/sounds/sword-sound.wav");
    }

    private void loadClip(String name, String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioIn.getFormat();
            System.out.println("Audio Format for " + name + ": " + format);

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
                System.out.println("Converted Audio Format for " + name + ": " + format);
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clips.put(name, clip);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playClip(String name) {
        Clip clip = clips.get(name);
        if (clip != null) {
            // Stop the clip if it is already playing
            if (clip.isRunning()) {
                clip.stop();
            }
            // Set the clip to the beginning and play it
            clip.setFramePosition(0);
            new Thread(clip::start).start();
        }
    }

    // Method to play footstep sound
    public void playFootstepSound() {
        playClip("footstep");
    }

    // Method to play goblin sound
    public void playGoblinSound() {
        playClip("goblin");
    }

    // Method to play goblin death sound
    public void playGoblinDeathSound() {
        playClip("goblinDeath");
    }

    // Method to play troll sound
    public void playTrollSound() {
        playClip("troll");
    }

    // Method to play troll death sound
    public void playTrollDeathSound() {
        playClip("trollDeath");
    }

    // Method to play sword sound
    public void playSwordSound() {
        playClip("sword");
    }
}
