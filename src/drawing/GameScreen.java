package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


public class GameScreen extends Canvas {
    public GameScreen(double width, double height){
        super(width, height);
    }


    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if(entity.isVisible()){
                entity.draw(gc);
            }
        }
    }
}
