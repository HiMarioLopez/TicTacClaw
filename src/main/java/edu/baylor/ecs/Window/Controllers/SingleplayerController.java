package edu.baylor.ecs.Window.Controllers;

import edu.baylor.ecs.Window.MasterWindow;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/** Author: Brandon Mork. */
public class SingleplayerController extends MasterWindow implements Initializable {

    protected SingleplayerController() {
        getWindow().setMaximized(true);
        getWindow().setMaxWidth(MAX_WIDTH);
        getWindow().setMaxHeight(MAX_HEIGHT);
        getWindow().setHeight(MAX_HEIGHT);
        getWindow().setWidth(MAX_WIDTH);
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) { }

}