package edu.baylor.ecs.MediaPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.List;

public class MediaBox {

    private static volatile MediaBox single_instance = null;
    private MediaPlayer mediaPlayer;
    private final List<String> songList;
    private Boolean playing;


    private MediaBox() {
        System.out.println("MediaBox created");
        songList = new ArrayList<>();
        songList.add("/music/Wii.mp3");
        playing = false;
    }

    public void playMediaBox() {
        System.out.println("The media has started");
        Media media = new Media(this.getClass()
                .getResource(songList.get(0)).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        playing = true;
    }

    public void stopMediaBox() {
        System.out.println("The media has stopped");
        if (playing) {
            mediaPlayer.stop();
            playing = false;
        }

    }

    public void changeVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    public boolean isPlaying() {
        return playing;
    }

    public static MediaBox getInstance() {
        if (single_instance == null) {
            synchronized (MediaBox.class) {
                if (single_instance == null) {
                    single_instance = new MediaBox();
                }
            }
        }

        return single_instance;
    }
}
