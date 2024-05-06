package scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import utils.Config;

public class HomeScene {
    private SceneControl sceneControl;
    private Scene scene;
    public HomeScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        Button startButton = new Button("Start");
        startButton.setOnMouseClicked(e -> sceneControl.showGameScene());
        root.getChildren().add(startButton);
        scene = new Scene(root, Config.sceneWidth, Config.sceneHeight);

    }

    public Scene getScene() {
        return scene;
    }
}
