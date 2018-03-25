package edu.baylor.ecs;

import javafx.scene.media.Media;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaBox {

    private Media sound;
    private MediaPlayer mediaPlayer;
    private String[] songList = {"/Wolves.m4a"};
    private Integer song = 0;
    private Boolean playing;


    public MediaBox(){
        System.out.println("MediaBox created");
    }

    protected void playMediaBox(){
        System.out.println("The media has started");
        sound = new Media(this.getClass().getResource("/Wolves.m4a").toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    protected void stopMediaBox(){
        System.out.println("The media has stopped");
        //playing = mediaPlayer.getStatus().equals(MediaPlayer.Status.READY);
        //if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            //mediaPlayer.stop();
        //System.out.println(playing);
        //if(!playing)
            //mediaPlayer.stop();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
