package scene;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Duration;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import utils.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeScene {
    private SceneControl sceneControl;
    private Scene scene;
    public HomeScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        Image backgroundImage = new Image(ClassLoader.getSystemResource("homeScene.png").toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(Config.sceneWidth);
        backgroundImageView.setFitHeight(Config.sceneHeight);
        root.getChildren().add(backgroundImageView);

        Text gameName = new Text("Magical World");
        gameName.setFill(Color.WHITE);
        gameName.setFont(Font.font("Arial", FontWeight.BOLD,60));
        StackPane.setAlignment(gameName, Pos.TOP_CENTER); // Align the text to the top center
        StackPane.setMargin(gameName, new Insets(90, 0, 0, 0)); // Set top margin
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.2), gameName);
        translateTransition.setFromY(0);
        translateTransition.setToY(-10);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);

        // Start the translate transition
        translateTransition.play();

        Button startButton = new Button("Start");
        startButton.setTextFill(Color.WHITE);
        startButton.setOnMouseClicked(e -> sceneControl.showItemScene());
        startButton.setFont(Font.font("Arial", FontWeight.BOLD,30));
        startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        StackPane.setMargin(startButton, new Insets(10, 0, 0, 0)); // Set bottom margin
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 1);"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"));

        Button HowToPlayButton = new Button("How to play");
        HowToPlayButton.setTextFill(Color.WHITE);
        HowToPlayButton.setOnMouseClicked(e -> {
        // Create a VBox to hold the instructions content
        VBox instructionsBox = new VBox();
        instructionsBox.setAlignment(Pos.TOP_CENTER);
        instructionsBox.setSpacing(10);
        instructionsBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
        instructionsBox.setPrefWidth(450); // Set preferred width
        instructionsBox.setPrefHeight(300); // Set preferred height

        // Add instructions Text or any other content to the VBox
        Text instructionsText = new Text("Instructions on how to play...");
        instructionsText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        instructionsBox.getChildren().add(instructionsText);


        // Create a Popup to show the instructions
        Popup howToPlayPopup = new Popup();
        howToPlayPopup.getContent().add(instructionsBox);
        howToPlayPopup.setX(HowToPlayButton.localToScreen(-120, 0).getX()); // Set X position
        howToPlayPopup.setY(HowToPlayButton.localToScreen(0, HowToPlayButton.getHeight()).getY() - 230); // Set Y position with additional offset
        howToPlayPopup.setAutoHide(true); // Popup will automatically hide when clicking outside



        // Show the Popup below the button
        howToPlayPopup.show(HowToPlayButton.getScene().getWindow());
    });


        HowToPlayButton.setFont(Font.font("Arial", FontWeight.BOLD,30));
        HowToPlayButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        StackPane.setAlignment(HowToPlayButton, Pos.CENTER); // Align to the center left
        StackPane.setMargin(HowToPlayButton, new Insets(140, 0, 0, 0)); // Set bottom margin
        HowToPlayButton.setOnMouseEntered(e -> HowToPlayButton.setStyle("-fx-background-color: rgba(0, 0, 0, 1);"));
        HowToPlayButton.setOnMouseExited(e -> HowToPlayButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"));


    Media media = new Media(ClassLoader.getSystemResource("jingle-family-174542.mp3").toString());

    // Create a MediaPlayer to play the background music
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    // Set the MediaPlayer to loop indefinitely
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    // Play the background music
        mediaPlayer.play();

        root.getChildren().addAll(gameName, startButton, HowToPlayButton);
        scene = new Scene(root, Config.sceneWidth, Config.sceneHeight);
    }

    public Scene getScene() {
        return scene;
    }
}
