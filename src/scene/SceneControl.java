package scene;

import javafx.stage.Stage;

public class SceneControl {
    private Stage stage;
    private HomeScene homeScene;
    private GameScene gameScene;
    private MonsterScene monsterScene;

    public SceneControl(Stage stage) {
        this.stage = stage;
    }

    public void showHomeScene() {
        this.homeScene = new HomeScene(this);
        stage.setScene(homeScene.getScene());
    }

    public void showGameScene() {
        this.gameScene = new GameScene(this);
        stage.setScene(gameScene.getScene());
    }

    public void showMonsterScene() {
        this.monsterScene = new MonsterScene(this);
        stage.setScene(monsterScene.getScene());
    }
}
