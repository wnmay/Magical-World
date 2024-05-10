package logic.item;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import sharedObject.IRenderable;

public abstract class BaseItem extends Entity implements IRenderable {
    public String name;

    public Rectangle solidArea;

    public BaseItem(String name) {
        this.name = name;
    }
    public abstract Image getImage();
    public abstract void useItem ();
}
