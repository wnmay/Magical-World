package logic.item.potion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.item.BaseItem;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class powerPotion extends BaseItem {
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    private Image image = RenderableHolder.powerPotion;
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
            initialX = (int) (Math.random() * (700));
            initialY = (int) (Math.random() * (450));
            drawn = true; // Mark as drawn
        }

        // Draw the image at the initial position
        gc.drawImage(image, initialX, initialY, 50, 50);
        solidArea = new Rectangle(initialX,initialY, 50, 50);
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void useItem() {
        //usage of this object
    }
}
