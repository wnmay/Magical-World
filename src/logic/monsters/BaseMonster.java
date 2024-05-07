package logic.monsters;

import javafx.scene.shape.Rectangle;
import logic.player.Player;

public class BaseMonster {
    public double x; // X-coordinate of the bat
    public double y; // Y-coordinate of the bat
    public double speed; // Speed of the bat's movement
    public Player player; // Reference to the player

    public Rectangle solidArea;

}
