package scene;

import drawing.FightScreen;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.game.BossSceneLogic;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import logic.item.weapon.Wand;
import sharedObject.RenderableHolder;
import utils.Config;

public class BossScene {
    private boolean coolDown = false;
    private StackPane root;
    private SceneControl sceneControl;
    private Scene scene;
    private BossSceneLogic logic;
    private FightScreen fightScreen;
    private AnimationTimer animationTimer;

    private boolean sceneState;

    public BossScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        root = new StackPane();
        logic = BossSceneLogic.getInstance();
        fightScreen = new FightScreen(Config.sceneWidth, Config.sceneHeight);
        root.getChildren().add(fightScreen);
        fightScreen.requestFocus();
        scene = new Scene(root);
        listener();
        gameloop();

    }

    private void attackOperation() {

        logic.getPlayer().Attack(logic.getMonsters());
        coolDown = true;

        Thread coolDownTime = new Thread(() ->{
            try{
                Thread.sleep(500);
                coolDown = false;
            } catch (InterruptedException ignored){

            }
        });
        coolDownTime.start();
    }

    public Scene getScene() {
        return scene;
    }

    public void listener(){
        scene.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.SPACE && !coolDown) {
                attackOperation();
            }
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
                            if(logic.getPlayer().getPlayerItem().size() >= index + 1){
                                if(!(logic.getPlayer().getPlayerItem().get(index) instanceof Wand)) {
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
                fightScreen.paintComponent();
                logic.logicUpdate();
//                sceneState = logic.sceneUpdate();
                if(logic.getPlayer().isGameOver()){
                    this.stop();
                    gameOver();
                }
                if (logic.getMonsters().isEmpty()){
                    logic.getPlayer().setGameOver(true);
                    this.stop();;
                    Media media = new Media(ClassLoader.getSystemResource("sound/mixkit-video-game-win-2016.wav").toString());
                    MediaPlayer itemPickupSound = new MediaPlayer(media);
                    itemPickupSound.setVolume(1);
                    itemPickupSound.play();
                    gameWin();
                }
            }
        };
        animation.start();
    }
    public void gameOver() {
        //decorate
        Rectangle gameOverBackground = new Rectangle(0,0,Config.sceneWidth,Config.sceneHeight);
        gameOverBackground.setFill(Color.BLACK);
        gameOverBackground.setOpacity(0.5);
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(new Font(85));
        gameOverText.setTranslateY(-200);
        gameOverText.setFill(Color.WHITE);
        Button restart = new Button("Play Again");
        restart.setTranslateY(30);
        BackgroundFill bgf = new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY);
        restart.setBackground(new Background(bgf));
        restart.setTextFill(Color.WHITE);
        restart.setFont(Font.font("Arial", FontWeight.NORMAL,40));
        Button home = new Button("Main Menu");
        home.setTranslateY(100);
        home.setBackground(new Background(bgf));
        home.setTextFill(Color.WHITE);
        home.setFont(Font.font("Arial", FontWeight.NORMAL,40));

        //method
        restart.setOnMouseEntered(e -> restart.setTextFill(Color.GREY));
        restart.setOnMouseExited(e -> restart.setTextFill(Color.WHITE));
        restart.setOnMouseClicked(e -> sceneControl.showItemScene());
        home.setOnMouseEntered(e -> home.setTextFill(Color.GREY));
        home.setOnMouseExited(e -> home.setTextFill(Color.WHITE));
        home.setOnMouseClicked(e -> sceneControl.showHomeScene());
        root.getChildren().addAll(gameOverBackground,gameOverText,restart,home);

        //reset game
        HomeScene.mediaPlayer.stop();
        RenderableHolder.getInstance().reset();
        Input.getKeyPressedList().clear();
        ItemSceneLogic.getInstance().reset();
        MonsterSceneLogic.getInstance().reset();
        logic.reset();
    }
    public void gameWin() {
        //decorate
        Rectangle gameOverBackground = new Rectangle(0,0,Config.sceneWidth,Config.sceneHeight);
        gameOverBackground.setFill(Color.BLACK);
        gameOverBackground.setOpacity(0.5);
        Text gameOverText = new Text("YOU WIN");
        gameOverText.setFont(new Font(85));
        gameOverText.setTranslateY(-200);
        gameOverText.setFill(Color.WHITE);
        Button restart = new Button("Play Again");
        restart.setTranslateY(30);
        BackgroundFill bgf = new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY);
        restart.setBackground(new Background(bgf));
        restart.setTextFill(Color.WHITE);
        restart.setFont(Font.font("Arial", FontWeight.NORMAL,40));
        Button home = new Button("Main Menu");
        home.setTranslateY(100);
        home.setBackground(new Background(bgf));
        home.setTextFill(Color.WHITE);
        home.setFont(Font.font("Arial", FontWeight.NORMAL,40));

        //method
        restart.setOnMouseEntered(e -> restart.setTextFill(Color.GREY));
        restart.setOnMouseExited(e -> restart.setTextFill(Color.WHITE));
        restart.setOnMouseClicked(e -> sceneControl.showItemScene());
        home.setOnMouseEntered(e -> home.setTextFill(Color.GREY));
        home.setOnMouseExited(e -> home.setTextFill(Color.WHITE));
        home.setOnMouseClicked(e -> sceneControl.showHomeScene());
        root.getChildren().addAll(gameOverBackground,gameOverText,restart,home);

        //reset game
        HomeScene.mediaPlayer.stop();
        RenderableHolder.getInstance().reset();
        Input.getKeyPressedList().clear();
        ItemSceneLogic.getInstance().reset();
        MonsterSceneLogic.getInstance().reset();
        logic.reset();
    }
}
