package scene;

import drawing.GameScreen;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import logic.game.GameLogic;
import javafx.scene.canvas.Canvas;
import sharedObject.RenderableHolder;

public class GameScene {
    private SceneControl sceneControl;
    private Scene scene;
    private GameLogic logic;
    private GameScreen gameScreen;
    private AnimationTimer animationTimer;
    private Canvas canvas;

    private boolean sceneState;

    public GameScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        logic = new GameLogic();
        gameScreen = new GameScreen(800, 600);
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();
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
                gameScreen.paintComponent();
                logic.logicUpdate();
                sceneState = logic.sceneUpdate();
                if(sceneState){
                    this.stop();
                    RenderableHolder.getInstance().reset();
                    Input.getKeyPressedList().clear();
                    sceneControl.showMonsterScene();
                }

            }
        };
        animation.start();
    }

}
