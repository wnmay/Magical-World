package logic.player;

import input.Input;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import logic.Attackable;
import logic.Entity;
import logic.item.BaseItem;
import logic.item.key;
import logic.map.Door;
import logic.monsters.BaseMonster;
import scene.MonsterScene;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Entity {
    private double velocityX;
    private double velocityY;

    private static Player instance;

    private double x,y;
    private static final int walk = 1;
    private int speed;

    public Rectangle solidArea;
    private WalkState walkState;
    public ArrayList<BaseItem> playerItem = new ArrayList<BaseItem>();
    public Door door;

    private boolean playerExitState;
    private int HP;
    private int mana;
    private int damage;
    public boolean gameOver = false;

    public Player(){
        setSpeed(3);
        setWalkState(WalkState.DOWN);
        setHP(20);
        setMana(20);
        this.damage = 2;
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
            velocityY = -speed;
        }
        if (Input.getKeyPressed(KeyCode.S)) {
            moveDownward();
            setWalkState(WalkState.DOWN);
            velocityY = speed;
        }
        if(Input.getKeyPressed(KeyCode.D)){
            moveRight();
            setWalkState(WalkState.RIGHT);
            velocityX = speed;
        }
        if (Input.getKeyPressed(KeyCode.A)) {
            moveLeft();
            setWalkState(WalkState.LEFT);
            velocityX = -speed;
        }

    }

    public void setPosition (double x, double y) {
        this.x = x;
        this.y = y;
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
    public void checkCollisionItem(ArrayList<BaseItem> items) {
        for (BaseItem item : items) {
            if (item != null && solidArea.intersects(item.solidArea.getBoundsInLocal())) {
                pickUpItem(item);
                items.remove(item);
                RenderableHolder.getInstance().remove((IRenderable) item);
                break;
            }
        }
    }

    public boolean checkExitScene() {
        // Check if the player's solid area intersects with the door's area
        if (solidArea.intersects(Door.getInstance().getDoorArea().getBoundsInLocal())) {
            // Iterate through the player's items
            for (BaseItem item : getPlayerItem()) {
                // Check if the player has the key item
                if (item instanceof key) {
                    getPlayerItem().remove(item);
                    return true; // Player has the key, allow exit
                }
            }
        }
        return false; // Player does not have the key, cannot exit
    }
    public void pickUpItem(BaseItem item) {
        playerItem.add(item);
        // Perform actions to pick up the item
        System.out.println("Player picked up: " + item.name);

        // You can add any additional logic here, such as updating player's inventory, score, etc.
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void getAttacked(ArrayList<BaseMonster> monsters) {
        for (BaseMonster monster : monsters) {
            if (solidArea.getBoundsInParent().intersects(monster.solidArea.getBoundsInParent())) {
                double dx = x - monster.x;
                double dy = y - monster.y;

                // Normalize direction vector
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance != 0) {
                    dx /= distance;
                    dy /= distance;
                }
                x += 20 * dx;
                y += 20 * dy;

                if (this.getHP() - monster.getDamage() <= 0){
                    System.out.println("player died");
                    gameOver = true;
                } else {
                    this.setHP(this.getHP() - monster.getDamage());
                    System.out.println(monster.name + " attack Player, Player HP:" + this.HP);
//                    x = x + 20;
//                    y = y + 20;
                }
            }
        }
    }


    public void Attack(ArrayList<BaseMonster> monsters) {
        if (Input.getKeyPressed(KeyCode.SPACE) && monsters != null) {
            Iterator<BaseMonster> iterator = monsters.iterator();
            while (iterator.hasNext()) {
                BaseMonster monster = iterator.next();
                if (solidArea.getBoundsInParent().intersects(monster.solidArea.getBoundsInParent())) {
                    double dx = monster.x - x;
                    double dy = monster.y - y;

                    // Normalize direction vector
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance != 0) {
                        dx /= distance;
                        dy /= distance;
                    }

                    // Adjust monster's position in the opposite direction
                    monster.x += 20 * dx;
                    monster.y += 20 * dy;


                    if (monster.getHP() - this.getDamage() <= 0) {
                        iterator.remove(); // Remove the current monster safely
                        RenderableHolder.getInstance().remove((IRenderable) monster);
                        System.out.println(monster.name + " died");
                    } else {
                        monster.setHP(monster.getHP() - this.getDamage());
                        System.out.println(monster.name + " was attacked by Player, HP: " + monster.getHP());
//                        monster.x -= 30;
//                        monster.y -= 30;
                    }
                }
            }
        }
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = Math.min(HP,20);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDamage() {
        return damage;
    }

    public ArrayList<BaseItem> getPlayerItem() {
        return playerItem;
    }

}
