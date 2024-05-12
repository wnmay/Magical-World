package logic.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Key extends BaseItem implements IRenderable {
    private boolean drawn = false;
    private Image image = RenderableHolder.key;

    public Key() {
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
            this.x = (int) (Math.random() * (700));
            this.y = (int) (Math.random() * (450 - 80 + 1)) + 80;
            drawn = true;
        }

        // Draw the image at the initial position
        gc.drawImage(image, x, y, 35, 35);
        solidArea = new Rectangle(x, y, 35, 35);
    }
    public Image getImage() {
        return image;
    }

    @Override
    public void useItem() {

    }
}
