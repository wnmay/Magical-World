package scene;

import javafx.stage.Stage;

public class SceneControl {
    private Stage stage;
    private HomeScene homeScene;
    private ItemScene gameScene;
    private MonsterScene monsterScene;

    private BossScene bossScene;

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

    public void showBossScene() {
        this.bossScene = new BossScene(this);
        stage.setScene(bossScene.getScene());
    }
}
