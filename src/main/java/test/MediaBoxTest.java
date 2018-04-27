package test;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** The only function we can test is the getInstance because.
 *  we cant test JavaFX functions unless we run main */
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
}
