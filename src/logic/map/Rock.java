package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Rock implements IRenderable {
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    public Rectangle solidArea;
    @Override
    public int getZ(){
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (!drawn) {
            // Generate random initial position only once
            initialX = (int) (Math.random() * (680));
            initialY = (int) (Math.random() * (430));
            drawn = true; // Mark as drawn
        }

        // Draw the image at the initial position
        gc.drawImage(RenderableHolder.rock, initialX, initialY, 70, 70);
        this.solidArea = new Rectangle(initialX,initialY, 70, 70);
    }
}

