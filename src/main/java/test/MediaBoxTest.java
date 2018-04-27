package test;

import edu.baylor.ecs.App.Main;
import edu.baylor.ecs.MediaPlayer.MediaBox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** The MediaBox testing. */
class MediaBoxTest {

    /** Two MediaBoxes used for testing. */
    private MediaBox temp, temp2;

    /** Verify the MediaBox is a singleton. */
    @Test
    void getInstance() {
        assertEquals(MediaBox.getInstance(), MediaBox.getInstance());
    }

    /** Verify the MediaBox is playing. */
    @Test
    void playMediaBox() {
        String[] command = new String[1];
        Main.main(command);
        MediaBox.getInstance().playMediaBox();
        assertTrue(MediaBox.getInstance().isPlaying());
    }
}
