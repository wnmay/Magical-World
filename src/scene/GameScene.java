package scene;

import drawing.GameScreen;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import logic.item.BaseItem;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class GameScene {
    private SceneControl sceneControl;
    private Scene scene;
    private GameLogic logic;
    private GameScreen gameScreen;
    private AnimationTimer animationTimer;
    private Canvas canvas;

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
            }
        };
        animation.start();

    }

}
