package edu.baylor.ecs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {


    private MasterWindow master;
    private String songFile = "K:/IntelliJ/Projects/TicTacClaw/src/main/resources/Wolves.m4a";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        Media sound = new Media(this.getClass().getResource("/Wolves.m4a").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();
//        AudioClip note = new AudioClip(this.getClass().getResource("/Wolves.mp3").toString());
//        note.play(100);
       // System.out.println(note.isPlaying());
        //        //MasterWindow master = new MasterWindow(root, window,  loginScene);



        master = new MasterWindow(window);

        //window.setScene(loginScene);
        master.getWindow().setScene(master.getLoginScene());
        master.getWindow().show();

        //exit request
        master.getWindow().setOnCloseRequest(e -> {
            //override the close request
            e.consume();
            master.closeProgram(master.getWindow());
            //mediaPlayer.stop();
            //closeProgram();
        });
    }

    public void closeProgram() {
        boolean result = ExitBox.display("Alert Window", "Do you really want to leave?");
        if (result) {
            System.out.println("Saving files...");
            master.getWindow().close();
        }
    }

    private void isAccountValid(TextField input, String message) {
        try {
            String user = message;
        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }

    public String getSongFile() {
        return songFile;
    }

    public void setSongFile(String songFile) {
        this.songFile = songFile;
    }
}

//REFERENCE CODE HERE
//loginScene = new Scene(root, 450, 500);
/*
        Label homeLabel = new Label("This is the home screen");
        Label profileLabel = new Label("This is your profile");

        //profileButton should be in homeScene
        profileButton = new Button("Your Profile");
        profileButton.setOnAction(e -> window.setScene(profileScene));

        //homeButton should be in profileScene
        homeButton = new Button("Go back to Home");
        homeButton.setOnAction(e -> window.setScene(homeScene));

        //exitButton & exit game
        exitButton = new Button("Exit");
        exitButton.setOnAction(e -> closeProgram());


        //newAccountButton
        newAccountButton = new Button("Make an Account");
        //newAccountButton.setOnAction(e -> );

        //loginButton
        TextField nameInput = new TextField();
        loginButton = new Button("Log In");
        loginButton.setOnAction(e -> isAccountValid(nameInput, nameInput.getText()) );


        //GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        //grid.setAlignment(Pos.CENTER);
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        final short centerX = 5;
        final short centerY = 18;
        GridPane.setConstraints(nameLabel, (centerX-1), centerY);
        GridPane.setConstraints(passwordLabel,centerX-1,centerY+1);

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("username");
        TextField passwordInput = new TextField();
        passwordInput.setPromptText("password");

        GridPane.setConstraints(usernameInput,centerX,centerY);
        GridPane.setConstraints(passwordInput,centerX,centerY+1);

        //Add buttons to grid
        GridPane.setConstraints(loginButton,centerX,centerY+2);
        GridPane.setConstraints(exitButton,centerX,centerY+3);
        GridPane.setConstraints(newAccountButton,centerX,centerY-1);

        grid.getChildren().addAll(nameLabel,usernameInput,passwordLabel,passwordInput,loginButton,profileButton,exitButton,newAccountButton);


        //Home Layout
        //VBox layout1 = new VBox(20);
        //layout1.getChildren().addAll(homeLabel,profileButton,exitButton);
        homeScene = new Scene(grid,500,500);
        homeScene.getStylesheets().add("com/company/login.css");

        //Profile layout
        VBox layout2 = new VBox(50);
        layout2.getChildren().addAll(profileLabel,homeButton);
        profileScene = new Scene(layout2,300,300);

        window.setScene(homeScene);
        window.setTitle("Tic-Tac-Claw");
        window.show();

//        primaryStage.setTitle("Tic-Tac-Claw");
//
//        //look in "this" class for the handle method, should make an event class
//        playButton = new Button("Play");
//        playButton.setOnAction(e -> System.out.println("You press play"));
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(playButton);
//
//        Scene scene = new Scene(layout,300,250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        */
