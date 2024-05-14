package logic.item.potion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.ItemSceneLogic;
import logic.item.BaseItem;
import javafx.scene.image.Image;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class manaPotion extends BaseItem {
    private boolean drawn = false;
    private Image manaPotion = new Image(ClassLoader.getSystemResource("item/ManaPotion.gif").toString());;


    public manaPotion() {
        super("Mana Potion");
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
        gc.drawImage(manaPotion, x, y, 50, 50);
        solidArea = new Rectangle(x,y, 50, 50);
    }

    public Image getImage() {
        return manaPotion;
    }

    @Override
    public void useItem() {
        Player player = ItemSceneLogic.getInstance().getPlayer();
        player.setMana(player.getMana() + 5);
    }
}
