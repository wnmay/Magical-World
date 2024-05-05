package logic.item;

import javafx.scene.image.Image;

public class BaseItem {
    public String name;
    public Image image;
    public int worldX, worldY;

    public BaseItem(String name, Image image, int worldX, int worldY) {
        this.name = name;
        this.image = image;
        this.worldX = worldX;
        this.worldY = worldY;
    }
}
