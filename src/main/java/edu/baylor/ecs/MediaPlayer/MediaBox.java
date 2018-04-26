package edu.baylor.ecs.MediaPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.List;

/** Author: Brandon Mork. */
public final class MediaBox {

    private static volatile MediaBox singleInstance = null;
    private MediaPlayer mediaPlayer;
    private final List<String> songList;
    private Boolean playing;


    /** Author: Brandon Mork. */
    private MediaBox() {
        System.out.println("MediaBox created");
        songList = new ArrayList<>();
        songList.add("/music/Wii.mp3");
        playing = false;
    }

    /** Author: Brandon Mork. */
    public void playMediaBox() {
        System.out.println("The media has started");
        Media media = new Media(this.getClass()
                .getResource(songList.get(0)).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        playing = true;
    }

    /** Author: Brandon Mork. */
    public void stopMediaBox() {
        System.out.println("The media has stopped");
        if (playing) {
            mediaPlayer.stop();
            playing = false;
        }

    }

    /** Author: Brandon Mork. */
    public void changeVolume(final double volume) {
        mediaPlayer.setVolume(volume);
    }

    /** Author: Brandon Mork. */
    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    /** Author: Brandon Mork. */
    public boolean isPlaying() {
        return playing;
    }

    /** Author: Brandon Mork. */
    public static MediaBox getInstance() {
        if (singleInstance == null) {
            synchronized (MediaBox.class) {
                if (singleInstance == null) {
                    singleInstance = new MediaBox();
                }
            }
        }

        return singleInstance;
    }
}
