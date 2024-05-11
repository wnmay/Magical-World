package logic.item;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import logic.item.potion.healPotion;
import logic.item.potion.manaPotion;
import logic.item.potion.powerPotion;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.Random;

public abstract class BaseItem extends Entity implements IRenderable {
    public String name;

    public Rectangle solidArea;

    public BaseItem(String name) {
        this.name = name;
    }
    public abstract Image getImage();
    public abstract void useItem ();
    public double x, y;



}
