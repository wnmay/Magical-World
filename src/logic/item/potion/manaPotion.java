package logic.item.potion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.ItemSceneLogic;
import logic.item.BaseItem;
import javafx.scene.image.Image;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class manaPotion extends BaseItem {
    public manaPotion() {
        super("Mana Potion");
    }
    private int initialX;
    private int initialY;
    private boolean drawn = false;
    private Image image = RenderableHolder.manaPotion;
    public double x;
    public double y;

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
            this.x = initialX;
            this.y = initialY;
        }

        // Draw the image at the initial position
        gc.drawImage(image, x, y, 50, 50);
        solidArea = new Rectangle(x,y, 50, 50);
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void useItem() {
        Player player = ItemSceneLogic.getInstance().getPlayer();
        player.setMana(player.getMana() + 5);
    }
}
