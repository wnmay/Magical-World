package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.Attackable;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Bat extends BaseMonster {

    public Bat(double x, double y, double speed, Player player) {
        super("Bat");
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.player = player;
        this.HP = 1;
        this.damage = 1;
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
    public void getAttacked() {
        setHP(getHP()-player.getDamage());
        System.out.println("Player attack");
    }

    @Override
    public void Attack() {
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

