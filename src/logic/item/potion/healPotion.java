package logic.item.potion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.ItemSceneLogic;
import logic.item.BaseItem;
import javafx.scene.image.Image;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class healPotion extends BaseItem {
    public healPotion() {
        super("Heal Potion");
    }
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    private Image image = RenderableHolder.healPotion;

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
    public void useItem () {
        Player player = ItemSceneLogic.getInstance().getPlayer();
        player.setHP(player.getHP() + 5);
    }
}
