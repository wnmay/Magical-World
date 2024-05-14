package scene;

import javafx.scene.input.KeyEvent;

public interface FightScene {
    public void attackOperation(KeyEvent event);
    public void gameOver();
}
