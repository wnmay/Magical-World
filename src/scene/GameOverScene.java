package scene;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import utils.Config;

public class GameOverScene {
    private SceneControl sceneControl;
    private Scene scene;
    public GameOverScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        Button startButton = new Button("Restart");
        startButton.setOnMouseClicked(e -> sceneControl.showHomeScene());
        root.getChildren().add(startButton);
        scene = new Scene(root, Config.sceneWidth, Config.sceneHeight);

    }

    public Scene getScene() {
        return scene;
    }
}
