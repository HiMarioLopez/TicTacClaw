package test;

import edu.baylor.ecs.App.Main;
import edu.baylor.ecs.MediaPlayer.MediaBox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/** The MediaBox testing */
class MediaBoxTest {

    /** Two MediaBoxes used for testing. */
    private MediaBox temp, temp2;

    /** Verify the MediaBox is a singleton. */
    @Test
    void getInstance() {
        temp = MediaBox.getInstance();
        temp2 = MediaBox.getInstance();

        assertEquals(temp, temp2);
    }

    /** Verify the MediaBox is playing. */
    @Test
    void playMediaBox() {
        String[] command = new String[1];
        Main.main(command);
        MediaBox.getInstance().playMediaBox();
        assert(MediaBox.getInstance().isPlaying());
    }
}
