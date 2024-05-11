package logic.monsters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class Boss extends BaseMonster{
    public Boss(double x, double y, double speed, Player player) {
        super("Boss");
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.player = player;
        this.HP = 20;
        this.damage = 5;
    }
    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.slimeRight, x, y,133,122);
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
        solidArea = new Rectangle(x, y, 133, 122);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void getAttacked() {
        setHP(getHP()-player.getDamage());
        System.out.println("Player attack");
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
