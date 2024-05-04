import drawing.GameScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.GameLogic;

public class Main extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Magical World");

        GameLogic logic = new GameLogic();
        GameScreen gameScreen = new GameScreen(800, 600);
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();

        stage.show();

        Thread thread=new Thread(()->{
            gameScreen.paintComponent();
        });
        thread.start();
    }
}