package scene;

import javafx.stage.Stage;

public class SceneControl {
    private Stage stage;
    private HomeScene homeScene;
    private ItemScene gameScene;
    private MonsterScene monsterScene;

    private GameOverScene gameOverScene;

    public SceneControl(Stage stage) {
        this.stage = stage;
    }

    public void showHomeScene() {
        this.homeScene = new HomeScene(this);
        stage.setScene(homeScene.getScene());
    }

    public void showItemScene() {
        this.gameScene = new ItemScene(this);
        stage.setScene(gameScene.getScene());
    }

    public void showMonsterScene() {
        this.monsterScene = new MonsterScene(this);
        stage.setScene(monsterScene.getScene());
    }

    public void showGameOverScene() {
        this.gameOverScene = new GameOverScene(this);
        stage.setScene(gameOverScene.getScene());
    }
}
