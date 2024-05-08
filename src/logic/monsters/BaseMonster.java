package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.Attackable;
import logic.player.Player;
import sharedObject.IRenderable;

public class BaseMonster implements IRenderable, Attackable{
    public double x; // X-coordinate of the bat
    public double y; // Y-coordinate of the bat
    public double speed; // Speed of the bat's movement
    public Player player; // Reference to the player

    public Rectangle solidArea;
    public int HP;

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
