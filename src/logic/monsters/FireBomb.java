package logic.monsters;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.Attackable;
import logic.Entity;
import logic.game.BossSceneLogic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.Random;

public class FireBomb implements IRenderable {
    private boolean visble;
    private double x;
    private double y;
    public Player player;

    public Rectangle solidArea;
    public int damage;

    public FireBomb(Player player) {
        visble = true;
        this.player = player;
        Random random = new Random();
        x = random.nextDouble() * (Config.sceneWidth - Config.playerWidth - 50);
        y = random.nextDouble() * (Config.sceneHeight - Config.playerWidth - 50);
        this.damage = 2;
        cycle();
    }
    public void cycle() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    visble = false;
                })
        );

        timeline.play();
    }
    public Player getPlayer() {
        return player;
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
//        Color color = Color.rgb(255, 0, 0, 0.5);
//        gc.setFill(color);
//        gc.fillOval( x, y, Config.playerWidth+100, 50);

//        solidArea = new Rectangle(x,y,Config.playerWidth+100,50);
        gc.drawImage(RenderableHolder.fireBomb,x,y,Config.playerWidth+50,Config.playerWidth+50);
        solidArea = new Rectangle(x ,y, Config.playerWidth+50, Config.playerWidth+50);
    }

    public double getX() {
            return x;
        }

    public double getY() {
            return y;
        }
}