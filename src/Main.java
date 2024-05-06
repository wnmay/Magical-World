import javafx.application.Application;
import javafx.stage.Stage;
import scene.SceneControl;

public class Main extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        SceneControl sceneControl = new SceneControl(stage);
        sceneControl.showHomeScene();
        stage.setTitle("Magical World");
        stage.setResizable(false);
        stage.show();
    }
}
