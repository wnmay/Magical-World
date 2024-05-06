package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.awt.*;

public class MonsterScreen extends Canvas {
    public MonsterScreen(double width, double height){
        super(width, height);

    }


    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            entity.draw(gc);
        }
    }

}
