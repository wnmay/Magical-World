package logic.item.weapon;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.item.BaseItem;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Wand extends BaseItem implements IRenderable {

    private boolean drawn = false;
    private Image wand = new Image(ClassLoader.getSystemResource("item/Wand.gif").toString());;


    public Wand() {
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
            this.x = (int) (Math.random() * (700));
            this.y = (int) (Math.random() * (450 - 80 + 1)) + 80;
            drawn = true;
        }

        // Draw the image at the initial position
        gc.drawImage(wand, x, y, 50, 50);
        solidArea = new Rectangle(x, y, 50, 50);
    }
    public Image getImage() {
        return wand;
    }

    @Override
    public void useItem() {
        //usage of this object
    }
}
