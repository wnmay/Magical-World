package logic.player;

import javafx.scene.canvas.GraphicsContext;
import logic.Entity;
import logic.game.MonsterSceneLogic;
import sharedObject.RenderableHolder;

public class Magic extends Entity {
    private Player player;
    private double x,y;
    public Magic() {
        player = MonsterSceneLogic.getInstance().getPlayer();
        this.y = player.getY();
        this.x = player.getX();
    }
    public void update() {
        if(player.getWalkState() == WalkState.RIGHT) {
            this.x += 7;
        }
        if(player.getWalkState() == WalkState.LEFT) {
            this.x -= 7;
        }
        if(player.getWalkState() == WalkState.UP) {
            this.y -= 7;
        }
        if(player.getWalkState() == WalkState.DOWN) {
            this.y += 7;
        }
    }
    @Override
    public int getZ () {
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.lightBall,x,y,30,30);
    }

}
