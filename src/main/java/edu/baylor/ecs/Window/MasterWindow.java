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
    private static Stage window;
    private static Scene currentScene;
    public static final double MAX_WIDTH = (int) Screen.getPrimary().getVisualBounds().getWidth();
    protected static final double MAX_HEIGHT = (int) Screen.getPrimary().getVisualBounds().getHeight();

    /** Author: Brandon Mork. */
    protected MasterWindow() {
        System.out.println("Ceated MasterWindow");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(window);
        });
    }

    /** Author: Brandon Mork. */
    public MasterWindow(final Stage otherWindow) {
        window = otherWindow;
        window.setTitle("Tic-Tac-Claw");
    }

    /** Author: Brandon Mork. */
    public final void closeProgram(final Stage window) {
        ExitBox exitBox = new ExitBox("Alert Window", "Do you really want to leave?");
        boolean result = exitBox.exitDisplay();

        if (result) {
            System.out.println("Saving files...");
            MediaBox.getInstance().stopMediaBox();
            window.close();
        }

    }

    /** Author: Brandon Mork. */
    protected final void connectToSingle() throws IOException {
        connect("/fxml/singlePlayer.fxml", "/css/singlePlayer.css");
    }

    /** Author: Brandon Mork. */
    protected final void connectToSetting() throws IOException {
        connect("/fxml/settingScreen.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork. */
    protected final void connectToHome() throws IOException {
        connect("/fxml/homeScreen.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork. */
    public final void connectToLogin() throws IOException {
        connect("/fxml/loginScreen.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork. */
    protected final void connectHowToPlay() throws IOException {
        connect("/fxml/howToPlay.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork. */
    protected final void connectToWinBox()throws IOException {
        connect("/fxml/winBox.fxml", "/css/default.css");
    }

    /** Author: Brandon Mork. */
    protected final void defaultInit() {
        double height = 600;
        double width = 500;

        window.setMinWidth(width);
        window.setMaxWidth(width);

        window.setMinHeight(height);
        window.setMaxHeight(height);

        window.setWidth(width);
        window.setHeight(height);
    }

    /** Author: Brandon Mork. */
    protected static Scene getCurrentScene() {
        return currentScene;
    }

    /** Author: Brandon Mork. */
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

    /** Author: Brandon Mork. */
    protected final void setWindow(final Stage other) {
        window = other;
    }

    /** Author: Brandon Mork. */
    protected final void backToHome() throws IOException {
        this.connectToHome();
        window.setScene(getCurrentScene());
        window.show();
    }

    /** Author: Brandon Mork. */
    private void connect(final String fxml, final String css) throws IOException {
        Parent rootParent = FXMLLoader.load(MasterWindow.class.getResource(fxml));
        currentScene = new Scene(rootParent);
        currentScene.getStylesheets().add(css);
    }
}
