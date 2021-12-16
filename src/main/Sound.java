package main;

import java.io.File;

import javax.sound.sampled.*;

public class Sound {
    public static final String background = "./res/Sound/background.wav";
    public static final String start = "./res/Sound/start.wav";

    public static final String new_level = "./res/Sound/NextLevel.wav";
    public static final String gameOver = "./res/Sound/gameover.wav";
    public static final String overtime = "./res/Sound/overtime.wav";
    public static final String victory = "./res/Sound/victory.wav";

    public static final String earnCoin = "./res/Sound/earnCoin.wav";
    public static final String get_item = "./res/Sound/getItem.wav";

    public static final String drop_bomb = "./res/Sound/bomSet.wav";
    public static final String bomb_explode = "./res/Sound/bombExplode.wav";
    public static final String enemy_Die1 = "./res/Sound/enemyDie.wav";
    public static final String enemy_Die2 = "./res/Sound/enemyDie1.wav";

    public static void playsound(String input) {
        try {
            File file = new File(input);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Clip bg;

    public static void playBackGround() {
        try {
            File file = new File("./res/Sound/background.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            bg = AudioSystem.getClip();
            bg.open(audioStream);
            bg.loop(2);
            bg.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mute() {
        if (bg.isRunning()) {
            bg.stop();
        }
    }
}
