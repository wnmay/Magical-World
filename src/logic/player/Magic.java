package logic.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import logic.game.MonsterSceneLogic;
import sharedObject.RenderableHolder;
import utils.Config;

public class Magic extends Entity {
    public Rectangle solidArea;
    private WalkState state;
    private Player player;
    private double x,y;
    public Magic() {
        player = MonsterSceneLogic.getInstance().getPlayer();
        this.y = player.getY();
        this.x = player.getX();
    }
    public void update() {
        if(state == WalkState.RIGHT) {
            this.x += 7;
        }
        if(state == WalkState.LEFT) {
            this.x -= 7;
        }
        if(state == WalkState.UP) {
            this.y -= 7;
        }
        if(state == WalkState.DOWN) {
            this.y += 7;
        }
        updateSolidArea();
    }
    public void setMagicState(WalkState state) {
        this.state = state;
    }
    @Override
    public int getZ () {
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.lightBall,x,y+25,30,30);
    }
    public void updateSolidArea() {
        this.solidArea = new Rectangle(x,y,30,30);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
