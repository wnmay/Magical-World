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
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.game.ItemSceneLogic;
import javafx.scene.canvas.Canvas;
import logic.game.MonsterSceneLogic;
import logic.item.weapon.Shield;
import sharedObject.RenderableHolder;
import utils.Config;

public class ItemScene {
    private SceneControl sceneControl;
    private Scene scene;
    private ItemSceneLogic logic;
    private GameScreen gameScreen;
    private AnimationTimer animationTimer;
    private boolean sceneState;

    public ItemScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;
        if (HomeScene.mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED){
            HomeScene.mediaPlayer.play();
        }

        StackPane root = new StackPane();
        logic = ItemSceneLogic.getInstance();
        gameScreen = new GameScreen(Config.sceneWidth, Config.sceneHeight);
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();
        scene = new Scene(root);
        listener();
        gameloop();

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
                if (logic.getChest().getSolidArea().contains(mouseEvent.getX(), mouseEvent.getY())) {
                    System.out.println("chest");
                    logic.getInventorySlot().setVisible(!logic.getInventorySlot().isVisible());
                }
                if(!logic.getInventorySlot().getSlotAreaList().isEmpty()){
                    for (Rectangle area:logic.getInventorySlot().getSlotAreaList()){
                        if (area.contains(mouseEvent.getX(),mouseEvent.getY())){
                            int index = logic.getInventorySlot().getSlotAreaList().indexOf(area);
                            System.out.println(index);
                            if(logic.getPlayer().getPlayerItem().size() >= index + 1){
                                if(!(logic.getPlayer().getPlayerItem().get(index) instanceof Shield)){
                                    logic.getPlayer().getPlayerItem().get(index).useItem();
                                    logic.getPlayer().getPlayerItem().remove(index);
                                }
                            }
                        }
                    }
                }
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
                    logic.getPlayer().checkWeapon();
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
    public Scene getScene() {
        return scene;
    }

}
