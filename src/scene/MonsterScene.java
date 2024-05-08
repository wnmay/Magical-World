package scene;

import drawing.MonsterScreen;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import logic.game.MonsterSceneLogic;
import logic.game.ItemSceneLogic;
import sharedObject.RenderableHolder;
import utils.Config;

public class MonsterScene {
    private SceneControl sceneControl;
    private Scene scene;
    private MonsterSceneLogic logic;
    private MonsterScreen monsterScreen;
    private AnimationTimer animationTimer;

    private boolean sceneState;

    public MonsterScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        logic = MonsterSceneLogic.getInstance();
        monsterScreen = new MonsterScreen(Config.sceneWidth, Config.sceneHeight);
        root.getChildren().add(monsterScreen);
        monsterScreen.requestFocus();
        scene = new Scene(root);
        listener();
        gameloop();

    }

    public Scene getScene() {
        return scene;
    }

    public void listener(){
        scene.setOnKeyPressed((KeyEvent event) -> {
            Input.setKeyPressed(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) ->{
            Input.setKeyPressed(event.getCode(), false);
        });
    }

    public void gameloop(){
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                monsterScreen.paintComponent();
                logic.logicUpdate();
                sceneState = logic.sceneUpdate();
                if(sceneState){
                    this.stop();
                    RenderableHolder.getInstance().reset();
                    Input.getKeyPressedList().clear();
                    ItemSceneLogic.getInstance().reset();
                    logic.reset();
                    sceneControl.showHomeScene();
                }
            }
        };
        animation.start();
    }
}
