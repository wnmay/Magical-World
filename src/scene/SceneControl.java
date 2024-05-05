package scene;

import javafx.stage.Stage;

public class SceneControl {
    private Stage stage;
    private HomeScene homeScene;
    private GameScene gameScene;

    public SceneControl(Stage stage) {
        this.stage = stage;
        this.homeScene = new HomeScene(this);
        this.gameScene = new GameScene(this);
    }

    public void showHomeScene() {
        stage.setScene(homeScene.getScene());
        stage.show();
    }

    public void showGameScene() {
        stage.setScene(gameScene.getScene());
        stage.show();
    }
}
