package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.Attackable;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Bat extends BaseMonster {

    public Bat(double speed, Player player) {
        super("Bat");
        this.x = (int) (Math.random() * (700));
        this.y = (int) (Math.random() * (450));
        this.speed = speed;
        this.player = player;
        this.HP = 2;
        this.damage = 1;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.bat, x, y,64,64);
    }
    @Override
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

    @Override
    public int getHP() {
        return super.getHP();
    }
    @Override
    public void setHP(int HP) {
        super.setHP(HP);
    }

    @Override
    public int getDamage() {
        return super.getDamage();
    }
}

