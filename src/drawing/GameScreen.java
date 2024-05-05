package drawing;

import input.Input;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


public class GameScreen extends Canvas {
    public GameScreen(double width, double height){
        super(width, height);
        listener();

    }

    public void listener(){
        this.setOnKeyPressed((KeyEvent event) -> {
            Input.setKeyPressed(event.getCode(), true);
        });

        this.setOnKeyReleased((KeyEvent event) ->{
            Input.setKeyPressed(event.getCode(), false);
        });
    }

    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            entity.draw(gc);
        }
    }
}
