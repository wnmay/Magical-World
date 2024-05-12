package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.Attackable;
import logic.Entity;
import logic.player.Player;
import sharedObject.IRenderable;

public class BaseMonster extends Entity implements Attackable{
    public  String name;
    public double x; // X-coordinate of the bat
    public double y; // Y-coordinate of the bat
    public double speed; // Speed of the bat's movement
    public Player player; // Reference to the player

    public Rectangle solidArea;
    public int HP;
    public int damage;

    public BaseMonster(String name) {
        this.name = name;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
    public void update() {}
    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
