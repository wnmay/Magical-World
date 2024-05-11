package logic.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class broom extends BaseItem implements IRenderable {
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    private Image image = RenderableHolder.broom;

    public broom() {
        super("broom");
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
        gc.drawImage(image, initialX, initialY, 80, 80);
        solidArea = new Rectangle(initialX,initialY, 80, 80);
    }
    public Image getImage() {
        return image;
    }

    @Override
    public void useItem() {
        Player player = ItemSceneLogic.getInstance().getPlayer();
        player.setSpeed(5);
    }
}

