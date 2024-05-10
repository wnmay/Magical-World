package logic.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class key extends BaseItem implements IRenderable {
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    private Image image = RenderableHolder.key;

    public key() {
        super("key");
    }

    @Override
    public int getZ() {
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (!drawn) {
            // Generate random initial position only once
            initialX = (int) (Math.random() * (700));
            initialY = (int) (Math.random() * (450));
            drawn = true; // Mark as drawn
        }

        // Draw the image at the initial position
        gc.drawImage(image, initialX, initialY, 35, 35);
        solidArea = new Rectangle(initialX,initialY, 35, 35);
    }
    public Image getImage() {
        return image;
    }
}
