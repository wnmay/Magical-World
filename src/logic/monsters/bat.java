package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Bat implements IRenderable {

    private double x; // X-coordinate of the bat
    private double y; // Y-coordinate of the bat
    private double speed; // Speed of the bat's movement
    private Player player; // Reference to the player

    public Bat(double x, double y, double speed, Player player) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.player = player;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.bat, x, y);
    }

    public void update() {
        // Calculate direction from bat to player
        double dx = player.getX() - x;
        double dy = player.getY() - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Normalize direction vector
        if (distance != 0) {
            dx /= distance;
            dy /= distance;
        }

        // Move bat towards player
        x += dx * speed;
        y += dy * speed;
    }
}

