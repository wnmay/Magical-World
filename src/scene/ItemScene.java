package scene;

import drawing.GameScreen;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.game.ItemSceneLogic;
import javafx.scene.canvas.Canvas;
import logic.game.MonsterSceneLogic;
import sharedObject.RenderableHolder;
import utils.Config;

public class ItemScene {
    private SceneControl sceneControl;
    private Scene scene;
    private ItemSceneLogic logic;
    private GameScreen gameScreen;
    private AnimationTimer animationTimer;
    private Canvas canvas;

    private boolean sceneState;

    public ItemScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        StackPane root = new StackPane();
        logic = ItemSceneLogic.getInstance();
        gameScreen = new GameScreen(Config.sceneWidth, Config.sceneHeight);
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

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(logic.getChest().getSolidArea().contains(mouseEvent.getX(),mouseEvent.getY())){
                    System.out.println("chest");
                }
                System.out.println("click");
            }
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
                    logic.getPlayer().setPosition(200, 200);
                    sceneControl.showMonsterScene();
                }

            }
        };
        animation.start();
    }




}
