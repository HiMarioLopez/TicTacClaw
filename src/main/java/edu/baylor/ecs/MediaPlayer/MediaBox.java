package edu.baylor.ecs.MediaPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.ArrayList;
import java.util.List;

/** Author: Brandon Mork. */
public final class MediaBox {

    /** the single instance of the class MediaBox.
     */
    private static volatile MediaBox singleInstance = null;

    /** the MediaPlayer that will play Media.
     */
    private MediaPlayer mediaPlayer;

    /** the list of songs.
     */
    private final List<String> songList;

    /** if the MediaPlayer is playing.
     */
    private Boolean playing;


    /** Author: Brandon Mork. */
    private MediaBox() {
        System.out.println("MediaBox created");
        songList = new ArrayList<>();
        songList.add("/music/Wii.mp3");
        playing = false;
    }

    /** Author: Brandon Mork.
     * play the MediaBox */
    public void playMediaBox() {
        System.out.println("The media has started");
        Media media = new Media(this.getClass()
                .getResource(songList.get(0)).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        playing = true;
    }

    /** Author: Brandon Mork.
     * stop the MediaBox */
    public void stopMediaBox() {
        System.out.println("The media has stopped");
        if (playing) {
            mediaPlayer.stop();
            playing = false;
        }

    }

    /** Author: Brandon Mork.
     * change the volume of the MediaBox
     * @param volume the volume that we will change to */
    public void changeVolume(final double volume) {
        mediaPlayer.setVolume(volume);
    }

    /** Author: Brandon Mork.
     * @return the current volume of the MediaBox */
    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    /** Author: Brandon Mork.
     * @return the condition if the MediaBox is playing */
    public boolean isPlaying() {
        return playing;
    }

    /** Author: Brandon Mork.
     * @return the MediaBox object */
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
