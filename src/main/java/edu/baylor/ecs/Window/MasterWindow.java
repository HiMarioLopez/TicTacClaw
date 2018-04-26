package edu.baylor.ecs.Window;

import edu.baylor.ecs.MediaPlayer.MediaBox;
import edu.baylor.ecs.PopUps.ExitBox;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/** Author: Brandon Mork. */
public class MasterWindow {

    /** The Stage that will be used for all controllers.
     */
    private static Stage window;

    /** The Scene that will be displayed on the Stage.
     */
    private static Scene currentScene;

    /** The width of the users screen.
     */
    public static final double MAX_WIDTH =
            (int) Screen.getPrimary().getVisualBounds().getWidth();

    /** The height of the users screen.
     */
    protected static final double MAX_HEIGHT =
            (int) Screen.getPrimary().getVisualBounds().getHeight();

    /** The default height of the window.
     */
    private static final int DEFAULT_HEIGHT = 600;

    /** The default width of the window.
     */
    private static final int DEFAULT_WIDTH = 500;


    /** Author: Brandon Mork. */
    protected MasterWindow() {
        System.out.println("Ceated MasterWindow");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(window);
        });
    }

    /** Author: Brandon Mork.
     * @param otherWindow the first Stage that we will use */
    public MasterWindow(final Stage otherWindow) {
        window = otherWindow;
        window.setTitle("Tic-Tac-Claw");
    }

    /** Author: Brandon Mork.
     * @param otherWindow the window that will be closed */
    public final void closeProgram(final Stage otherWindow) {
        ExitBox exitBox =
                new ExitBox("Alert Window", "Do you really want to leave?");
        boolean result = exitBox.exitDisplay();

        if (result) {
            System.out.println("Saving files...");
            MediaBox.getInstance().stopMediaBox();
            otherWindow.close();
        }

    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    protected final void connectToSingle() throws IOException {
        connect("/fxml/singlePlayer.fxml", "/css/singlePlayer.css");
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    protected final void connectToSetting() throws IOException {
        connect("/fxml/settingScreen.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    protected final void connectToHome() throws IOException {
        connect("/fxml/homeScreen.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    public final void connectToLogin() throws IOException {
        connect("/fxml/loginScreen.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    protected final void connectHowToPlay() throws IOException {
        connect("/fxml/howToPlay.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    protected final void connectToWinBox()throws IOException {
        connect("/fxml/winBox.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork. */
    protected final void defaultInit() {
        double height = DEFAULT_HEIGHT;
        double width = DEFAULT_WIDTH;

        window.setMinWidth(width);
        window.setMaxWidth(width);

        window.setMinHeight(height);
        window.setMaxHeight(height);

        window.setWidth(width);
        window.setHeight(height);
    }

    /** Author: Brandon Mork.
     * @return the currentScene being used */
    protected static Scene getCurrentScene() {
        return currentScene;
    }

    /** Author: Brandon Mork.
     * @return the current Window being used */
    protected static Stage getWindow() {
        return window;
    }

    /** Author: Brandon Mork. */
    public final void updateScene() {
        window.setScene(currentScene);
    }

    /** Author: Brandon Mork. */
    public final void display() {
        window.show();
    }

    /** Author: Brandon Mork.
     * @param other the Stage to set the window to */
    protected final void setWindow(final Stage other) {
        window = other;
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting */
    protected final void backToHome() throws IOException {
        this.connectToHome();
        window.setScene(getCurrentScene());
        window.show();
    }

    /** Author: Brandon Mork.
     * @throws IOException if error occurs connecting
     * @param fxml the FXML file that we will use
     * @param css the CSS file that we will use */
    private void connect(final String fxml, final String css)
            throws IOException {
        Parent rootParent =
                FXMLLoader.load(MasterWindow.class.getResource(fxml));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add(css);
    }
}
