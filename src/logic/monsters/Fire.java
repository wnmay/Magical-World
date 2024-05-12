package logic.monsters;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.Random;

public class Fire implements IRenderable {
    private boolean visble;
    private double x;
    private double y;
    public FireBomb fireBomb;

    public Rectangle solidArea;
    public int damage;

    public Fire(FireBomb fireBomb) {
        visble = true;
        this.fireBomb = fireBomb;
        x = fireBomb.getX();
        y = fireBomb.getY();
        this.damage = 2;
        cycle();
    }
    public void cycle() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(fireBomb.getPlayer().solidArea.getBoundsInParent().intersects(this.solidArea.getBoundsInParent())){
                fireBomb.getPlayer().setHP(fireBomb.getPlayer().getHP() - 3);
            }
            visble = false;
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @Override
    public boolean isVisible() {
        return visble;
    }

    @Override
    public int getZ() {
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.fireBomb,x,y,Config.playerWidth+100,Config.playerWidth+100);
        solidArea = new Rectangle(x ,y, Config.playerWidth+100, Config.playerWidth+100);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
