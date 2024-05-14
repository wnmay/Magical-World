package scene;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.util.Duration;
import utils.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomeScene {
    public static MediaPlayer mediaPlayer;
    private SceneControl sceneControl;
    private Scene scene;
    public HomeScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;
        StackPane root = new StackPane();
        ImageView imageView = backgroundImageView();
        Text gameName = gameName();
        translateTransition(gameName);
        Button startButton = startButton();
        Button HowToPlayButton = howToPlayButton();
        mediaPlayer();
        root.getChildren().addAll(imageView,gameName, startButton, HowToPlayButton);
        scene = new Scene(root, Config.sceneWidth, Config.sceneHeight);
    }

    private ImageView backgroundImageView() {
        Image backgroundImage = new Image(ClassLoader.getSystemResource("homeScene.png").toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(Config.sceneWidth);
        backgroundImageView.setFitHeight(Config.sceneHeight);
        return backgroundImageView;
    }

    private Text gameName() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/press-start-2p-font/Pixeboy-z8XGD.ttf"), 80);
        Text gameName = new Text("Magical World");
        gameName.setFill(Color.WHITE);
        gameName.setFont(customFont);
        StackPane.setAlignment(gameName, Pos.TOP_CENTER); // Align the text to the top center
        StackPane.setMargin(gameName, new Insets(90, 0, 0, 0)); // Set top margin
        return gameName;
    }
    private Button startButton() {
        Button startButton = new Button("Start");
        startButton.setTextFill(Color.WHITE);
        startButton.setOnMouseClicked(e -> sceneControl.showItemScene());
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/press-start-2p-font/Pixeboy-z8XGD.ttf"), 40);
        startButton.setFont(customFont);
        startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        StackPane.setMargin(startButton, new Insets(10, 0, 0, 0)); // Set bottom margin
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 1);"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"));
        return startButton;
    }
    private VBox instructionBox () {
        VBox instructionsBox = new VBox();
        instructionsBox.setAlignment(Pos.TOP_CENTER);
        instructionsBox.setSpacing(10);
        instructionsBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 10px;");
        instructionsBox.setPrefWidth(450); // Set preferred width
        instructionsBox.setPrefHeight(300); // Set preferred height
        return instructionsBox;
    }
    private Text instructionText() {
        Text instructionsText = new Text("How to play\n" + "\n" +
                "1. Use the W, A, S, and D keys to move your character.\n" + "\n" +
                "2. Press the spacebar to attack monsters.\n" + "\n" +
                "3. Click on chests to view your items and use them when needed.");
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/press-start-2p-font/Pixeboy-z8XGD.ttf"), 20);
        instructionsText.setWrappingWidth(400); // Set the wrapping width if needed
        instructionsText.setFont(customFont);
        return instructionsText;
    }

    private Text startText() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/press-start-2p-font/Pixeboy-z8XGD.ttf"), 24);
        Text startText = new Text("\n\nMagic awaits! Click anywhere\nto begin your mystical quest!");
        startText.setTextAlignment(TextAlignment.CENTER);
        startText.setWrappingWidth(400); // Set the wrapping width if needed
        startText.setFont(customFont);
        startText.setFill(Color.DARKBLUE);
        return startText;
    }
    private Button howToPlayButton() {
        Button HowToPlayButton = new Button("How to play");
        HowToPlayButton.setTextFill(Color.WHITE);
        HowToPlayButton.setOnMouseClicked(e -> {
            VBox instructionsBox = instructionBox();
            Text instructionsText = instructionText();
            Text StartText = startText();

            instructionsBox.getChildren().addAll(instructionsText, StartText);

            Popup howToPlayPopup = new Popup();
            howToPlayPopup.getContent().add(instructionsBox);
            howToPlayPopup.setX(HowToPlayButton.localToScreen(-120, 0).getX()); // Set X position
            howToPlayPopup.setY(HowToPlayButton.localToScreen(0, HowToPlayButton.getHeight()).getY() - 230); // Set Y position with additional offset
            howToPlayPopup.setAutoHide(true); // Popup will automatically hide when clicking outside
            howToPlayPopup.show(HowToPlayButton.getScene().getWindow());
        });
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/press-start-2p-font/Pixeboy-z8XGD.ttf"), 40);
        HowToPlayButton.setFont(customFont);
        HowToPlayButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);");
        StackPane.setAlignment(HowToPlayButton, Pos.CENTER); // Align to the center left
        StackPane.setMargin(HowToPlayButton, new Insets(140, 0, 0, 0)); // Set bottom margin
        HowToPlayButton.setOnMouseEntered(e -> HowToPlayButton.setStyle("-fx-background-color: rgba(0, 0, 0, 1);"));
        HowToPlayButton.setOnMouseExited(e -> HowToPlayButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"));
        return HowToPlayButton;
    }
    private void mediaPlayer() {
        Media media = new Media(ClassLoader.getSystemResource("sound/jingle-family-174542.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }
    private void translateTransition(Text gameName){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.2), gameName);
        translateTransition.setFromY(0);
        translateTransition.setToY(-10);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    public Scene getScene() {
        return scene;
    }
}
