package scene;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import logic.GameLogic;

public class GameScene {
    private SceneControl sceneControl;
    private Scene scene;
    private GameLogic logic;
    private GameScreen gameScreen;
    private AnimationTimer animationTimer;

    public GameScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        logic = new GameLogic();
        gameScreen = new GameScreen(800, 600);
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameScreen.paintComponent();
                logic.logicUpdate();
            }
        };
        animation.start();
        scene = new Scene(root);

    }

    public Scene getScene() {
        return scene;
    }

//    public void gameloop(){
//        AnimationTimer animation = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                gameScreen.paintComponent();
//                logic.logicUpdate();
//            }
//        };
//        animation.start();
//
//    }
}
