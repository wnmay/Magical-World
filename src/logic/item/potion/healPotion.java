package logic.item.potion;

import logic.item.BaseItem;
import javafx.scene.image.Image;

public class healPotion extends BaseItem {
    public healPotion(Image image, int worldX, int worldY) {
        super("Heal Potion", image, worldX, worldY);
    }
}
