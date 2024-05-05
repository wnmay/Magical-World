package logic.item.potion;

import javafx.scene.canvas.GraphicsContext;
import logic.item.BaseItem;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class powerPotion extends BaseItem implements IRenderable {
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    public powerPotion() {
        super("Power Potion");
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
        gc.drawImage(RenderableHolder.powerPotion, initialX, initialY, 50, 50);
    }
}
