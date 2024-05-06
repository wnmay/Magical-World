package logic.player;

import input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.item.BaseItem;
import logic.map.Door;
import scene.SceneControl;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.ArrayList;

public class Player implements IRenderable {

    private static Player instance;

    private double x,y;
    private static final int walk = 1;
    private int speed;

    public Rectangle solidArea;
    private WalkState walkState;
    public ArrayList<BaseItem> playerItem = new ArrayList<BaseItem>();
    public Door door;

    private boolean playerExitState;


    public Player(double x,double y){
        this.x=x;
        this.y=y;
        setSpeed(3);
        setWalkState(WalkState.DOWN);
    }

    public void moveUpward(){
        if(this.y >= 0){
            this.y -= walk * speed;
        }
    }
    public void moveDownward(){
        if(this.y <= Config.sceneHeight - Config.playerHeight){
            this.y += walk * speed;
        }
    }
    public void moveLeft(){
        if(this.x >= 0){
            this.x -= walk * speed;
        }

    }
    public void moveRight(){
        if(this.x <= Config.sceneWidth - Config.playerWidth){
            this.x += walk * speed;
        }

    }

    public void update(){
        if(Input.getKeyPressed(KeyCode.W)){
            moveUpward();
            setWalkState(WalkState.UP);
            System.out.println("up");
        }
        if (Input.getKeyPressed(KeyCode.S)) {
            moveDownward();
            setWalkState(WalkState.DOWN);
            System.out.println("down");
        }
        if(Input.getKeyPressed(KeyCode.D)){
            moveRight();
            setWalkState(WalkState.RIGHT);
            System.out.println("right");
        }
        if (Input.getKeyPressed(KeyCode.A)) {
            moveLeft();
            setWalkState(WalkState.LEFT);
            System.out.println("left");
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
            gc.drawImage(RenderableHolder.playerBack,x,y, Config.playerWidth,Config.playerHeight);
            solidArea = new Rectangle(x, y, Config.playerWidth, Config.playerHeight);
        }
        else if(getWalkState().equals(WalkState.DOWN)){
            gc.drawImage(RenderableHolder.playerFront,x,y,Config.playerWidth,Config.playerHeight);
            solidArea = new Rectangle(x, y, Config.playerWidth,Config.playerHeight);
        }
        else if(getWalkState().equals(WalkState.RIGHT)){
            gc.drawImage(RenderableHolder.playerRight,x,y,Config.playerWidth,Config.playerHeight);
            solidArea = new Rectangle(x, y, Config.playerWidth,Config.playerHeight);
        }
        else if(getWalkState().equals(WalkState.LEFT)){
            gc.drawImage(RenderableHolder.playerLeft,x,y,Config.playerWidth,Config.playerHeight);
            solidArea = new Rectangle(x, y, Config.playerWidth,Config.playerHeight);
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
    public boolean checkExitScene () {
        if(solidArea.intersects(Door.getInstance().getDoorArea().getBoundsInLocal())) {
            return true;
        }
        else{
            return false;
        }
    }
    public void pickUpItem(BaseItem item) {
        playerItem.add(item);
        // Perform actions to pick up the item
        System.out.println("Player picked up: " + item.name);

        // You can add any additional logic here, such as updating player's inventory, score, etc.
    }


    public ArrayList<BaseItem> getPlayerItem() {
        return playerItem;
    }
//    public static Player getInstance() {
//        if(instance == null){
//            instance = new Player(400,300);
//        }
//        return instance;
//    }
    //     public void checkExitScene () {
//        if(solidArea.intersects(Door.getInstance().getDoorArea().getBoundsInLocal())) {
//            setPlayerExitState(true);
//        }
//        else{
//            setPlayerExitState(false);
//        }
//     }
//     public void setPlayerExitState(boolean state) {
//        this.playerExitState = state;
//     }
//     public boolean getPlayerExitState() {
//        return this.playerExitState;
//     }
}
