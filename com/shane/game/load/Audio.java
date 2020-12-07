package com.shane.game.load;

import javax.sound.sampled.AudioInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio
{
    private static boolean startLoop;
    private static Clip clip;
    
    static {
        Audio.startLoop = false;
    }
    
    public static synchronized void playSound(final String path) {
        try {
            Audio.clip = AudioSystem.getClip();
            final AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(Object.class.getResourceAsStream(path)));
            Audio.clip.open(inputStream);
            Audio.clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setLoop(final boolean value) {
        Audio.startLoop = value;
    }
    
    public static boolean getLoop() {
        return Audio.startLoop;
    }
    
    public static void stopClip() {
        Audio.clip.stop();
    }
    
    public static void startClip() {
        Audio.clip.start();
    }
}
