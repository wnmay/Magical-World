package logic.player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import logic.game.MonsterSceneLogic;
import sharedObject.RenderableHolder;

public class Magic extends Entity {
    public Rectangle solidArea;
    private WalkState state;
    private Player player;
    private double x,y;
    private Image lightBall = new Image(ClassLoader.getSystemResource("player/lightBall.png").toString());

    public Magic() {
        player = MonsterSceneLogic.getInstance().getPlayer();
        this.y = player.getY();
        this.x = player.getX();
    }
    public void update() {
        if(state == WalkState.RIGHT) {
            this.x += 5;
        }
        if(state == WalkState.LEFT) {
            this.x -= 5;
        }
        if(state == WalkState.UP) {
            this.y -= 5;
        }
        if(state == WalkState.DOWN) {
            this.y += 5;
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
        gc.drawImage(lightBall,x,y + 25,30,30);
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
