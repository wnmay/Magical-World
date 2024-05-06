package logic.player;

import input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.item.BaseItem;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.ArrayList;

public class Player implements IRenderable {

    private double x,y;
    private static final int walk = 1;
    private int speed;

    public Rectangle solidArea;
    private WalkState walkState;
    public ArrayList<BaseItem> playerItem = new ArrayList<BaseItem>();

    public Player(double x,double y){
        this.x=x;
        this.y=y;
        setSpeed(3);
        setWalkState(WalkState.DOWN);
    }

    public void moveUpward(){
        this.y -= walk * speed;
    }
    public void moveDownward(){
        this.y += walk * speed;
    }
    public void moveLeft(){
        this.x -= walk * speed;
    }

    public void moveRight(){
        this.x += walk * speed;
    }

    public void update(){
        if(Input.getKeyPressed(KeyCode.W)){
            moveUpward();
            setWalkState(WalkState.UP);
        }
        if (Input.getKeyPressed(KeyCode.S)) {
            moveDownward();
            setWalkState(WalkState.DOWN);
        }
        if(Input.getKeyPressed(KeyCode.D)){
            moveRight();
            setWalkState(WalkState.RIGHT);
        }
        if (Input.getKeyPressed(KeyCode.A)) {
            moveLeft();
            setWalkState(WalkState.LEFT);
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = Math.max(speed, 0);
    }

    public WalkState getWalkState() {
        return walkState;
    }

    public void setWalkState(WalkState walkState) {
        this.walkState = walkState;
    }

    @Override
    public int getZ(){
        return 1;
    }

    @Override
    public void draw(GraphicsContext gc){
        if(getWalkState().equals(WalkState.UP)){
            gc.drawImage(RenderableHolder.playerBack,x,y,50,93);
            solidArea = new Rectangle(x, y, 50, 93);
        }
        else if(getWalkState().equals(WalkState.DOWN)){
            gc.drawImage(RenderableHolder.playerFront,x,y,50,93);
            solidArea = new Rectangle(x, y, 50, 93);
        }
        else if(getWalkState().equals(WalkState.RIGHT)){
            gc.drawImage(RenderableHolder.playerRight,x,y,50,93);
            solidArea = new Rectangle(x, y, 50, 93);
        }
        else if(getWalkState().equals(WalkState.LEFT)){
            gc.drawImage(RenderableHolder.playerLeft,x,y,50,93);
            solidArea = new Rectangle(x, y, 50, 93);
        }
    }
    public void checkCollision(ArrayList<BaseItem> items) {
        for (BaseItem item : items) {
            if (item != null && solidArea.intersects(item.solidArea.getBoundsInLocal())) {
                pickUpItem(item);
                items.remove(item);
                RenderableHolder.getInstance().remove((IRenderable) item);
                break;
            }
        }
    }
    public void pickUpItem(BaseItem item) {
        playerItem.add(item);
        // Perform actions to pick up the item
        System.out.println("Player picked up: " + item.name);

        // You can add any additional logic here, such as updating player's inventory, score, etc.
    }

}
