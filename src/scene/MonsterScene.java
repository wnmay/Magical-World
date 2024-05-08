package scene;

import drawing.MonsterScreen;
import input.Input;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.game.MonsterSceneLogic;
import logic.game.ItemSceneLogic;
import sharedObject.RenderableHolder;
import utils.Config;

public class MonsterScene {
    private StackPane root;
    private SceneControl sceneControl;
    private Scene scene;
    private MonsterSceneLogic logic;
    private MonsterScreen monsterScreen;
    private AnimationTimer animationTimer;

    private boolean sceneState;

    public MonsterScene(SceneControl sceneControl){
        this.sceneControl = sceneControl;

        root = new StackPane();
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
                    gameOver();
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
        restart.setTranslateY(75);
        BackgroundFill bgf = new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY);
        restart.setBackground(new Background(bgf));
        restart.setTextFill(Color.WHITE);
        restart.setFont(Font.font("Arial", FontWeight.NORMAL,40));

        //method
        restart.setOnMouseEntered(e -> restart.setTextFill(Color.GREY));
        restart.setOnMouseExited(e -> restart.setTextFill(Color.WHITE));
        restart.setOnMouseClicked(e -> sceneControl.showItemScene());
        root.getChildren().addAll(gameOverBackground,gameOverText,restart);

        //reset game
        RenderableHolder.getInstance().reset();
        Input.getKeyPressedList().clear();
        ItemSceneLogic.getInstance().reset();
        logic.reset();
    }
}
