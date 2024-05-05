import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.GameLogic;
import scene.SceneControl;

public class Main extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        SceneControl sceneControl = new SceneControl(stage);
        sceneControl.showHomeScene();
    }
}
