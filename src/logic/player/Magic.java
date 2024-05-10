package logic.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import logic.game.MonsterSceneLogic;
import sharedObject.RenderableHolder;

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
        gc.drawImage(RenderableHolder.lightBall,x,y,30,30);
        solidArea = new Rectangle( x, y, 30, 30);
    }

}
