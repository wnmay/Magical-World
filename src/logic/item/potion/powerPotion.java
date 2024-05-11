package logic.item.potion;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.game.MonsterSceneLogic;
import logic.item.BaseItem;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class powerPotion extends BaseItem {
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
            this.x = (int) (Math.random() * (700));
            this.y = (int) (Math.random() * (450));
            drawn = true;
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
        MonsterSceneLogic.getInstance().getPlayer().setDamage(MonsterSceneLogic.getInstance().getPlayer().getDamage()+2);
    }
}
