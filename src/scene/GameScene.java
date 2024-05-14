package scene;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public interface GameScene {
    public Scene getScene();
    public void listener();
    public void inventoryHandle(MouseEvent mouseEvent);
    public void gameloop();
}
