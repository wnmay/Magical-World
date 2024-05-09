package scene;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        StackPane.setMargin(startButton, new Insets(50, 0, 0, 0)); // Set bottom margin
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 1);"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);"));

        root.getChildren().addAll(gameName, startButton);
        scene = new Scene(root, Config.sceneWidth, Config.sceneHeight);
    }

    public Scene getScene() {
        return scene;
    }
}
