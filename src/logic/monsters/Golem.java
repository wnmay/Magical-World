package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Golem extends BaseMonster implements IRenderable {

    public Golem(double x, double y, double speed, Player player) {
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
        gc.drawImage(RenderableHolder.golem, x, y, 100, 100);
    }

    public void update() {
        // Calculate direction from bat to player
        double dx = player.getX() - this.x;
        double dy = player.getY() - this.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Normalize direction vector
        if (distance != 0) {
            dx /= distance;
            dy /= distance;
        }

        // Move bat towards player
        x += dx * speed;
        y += dy * speed;
        solidArea = new Rectangle(x, y, 50, 50);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}