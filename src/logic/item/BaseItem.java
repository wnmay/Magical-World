package logic.item;

import javafx.scene.image.Image;

public class BaseItem {
    public String name;
    public Image image;
    public int worldX, worldY;

    public BaseItem(String name) {
        this.name = name;
    }
}
