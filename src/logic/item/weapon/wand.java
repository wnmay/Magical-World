package logic.item.weapon;


import javafx.scene.canvas.GraphicsContext;
import logic.item.BaseItem;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.Random;

public class wand extends BaseItem implements IRenderable {
    private int initialX;
    private int initialY;
    private boolean drawn = false;

    public wand() {
        super("wand");
    }

    @Override
    public int getZ() {
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (!drawn) {
            // Generate random initial position only once
            initialX = (int) (Math.random() * (500 - 50));
            initialY = (int) (Math.random() * (500 - 50));
            drawn = true; // Mark as drawn
        }

        // Draw the image at the initial position
        gc.drawImage(RenderableHolder.wand, initialX, initialY, 50, 50);
    }

}
