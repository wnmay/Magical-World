package logic.player;

import input.Input;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.Attackable;
import logic.Entity;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import logic.item.BaseItem;
import logic.item.key;
import logic.item.potion.healPotion;
import logic.item.potion.manaPotion;
import logic.item.potion.powerPotion;
import logic.map.Door;
import logic.monsters.BaseMonster;
import scene.MonsterScene;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
    private boolean isUsingShield = false; // Flag to indicate whether the player is currently using the shield item
    private Timeline shieldTimer; // Timeline for shield duration

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
        if(ItemSceneLogic.getInstance().getBroom().isUsed()){
            if(getWalkState().equals(WalkState.UP)){
                gc.drawImage(RenderableHolder.broomBack,x,y, Config.playerWidthWBroom,Config.playerHeight);
            }
            else if(getWalkState().equals(WalkState.DOWN)){
                gc.drawImage(RenderableHolder.broomFront,x,y,Config.playerWidthWBroom,Config.playerHeight);
            }
            else if(getWalkState().equals(WalkState.RIGHT)){
                gc.drawImage(RenderableHolder.broomRight,x,y,Config.playerWidthWBroom,Config.playerHeight);
            }
            else if(getWalkState().equals(WalkState.LEFT)){
                gc.drawImage(RenderableHolder.broomLeft,x,y,Config.playerWidthWBroom,Config.playerHeight);
            }
            //draw shadow
            double shadowX = x;
            double shadowY = y + Config.playerHeight;
            double shadowYRadius = 20;
            gc.setFill(Color.rgb(0, 0, 0, 0.4));
            gc.fillOval(shadowX, shadowY + 5, Config.playerWidthWBroom, shadowYRadius);
        }
        else{
            if(getWalkState().equals(WalkState.UP)){
                gc.drawImage(RenderableHolder.playerBack,x,y, Config.playerWidth,Config.playerHeight);
            }
            else if(getWalkState().equals(WalkState.DOWN)){
                gc.drawImage(RenderableHolder.playerFront,x,y,Config.playerWidth,Config.playerHeight);
            }
            else if(getWalkState().equals(WalkState.RIGHT)){
                gc.drawImage(RenderableHolder.playerRight,x,y,Config.playerWidth,Config.playerHeight);
            }
            else if(getWalkState().equals(WalkState.LEFT)){
                gc.drawImage(RenderableHolder.playerLeft,x,y,Config.playerWidth,Config.playerHeight);
            }
        }
        solidArea = new Rectangle(x, y, Config.playerWidth,Config.playerHeight);
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
        Media media = new Media(ClassLoader.getSystemResource("sound/game-start-6104.mp3").toString());
        MediaPlayer itemPickupSound = new MediaPlayer(media);
        itemPickupSound.setVolume(1);
        itemPickupSound.play();

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
                    if (this.x <= Config.sceneWidth - Config.playerWidth && this.x >= 0) {
                        x += 20 * dx;
                    }
                    if (this.y <= Config.sceneHeight - Config.playerHeight && this.y >= 0) {
                        y += 20 * dy;
                    }
                    if(!isUsingShield()) {

                        if (this.getHP() - monster.getDamage() <= 0) {
                            System.out.println("player died");
                            gameOver = true;
                            Media media = new Media(ClassLoader.getSystemResource("sound/dead-8bit-41400.mp3").toString());
                            MediaPlayer itemPickupSound = new MediaPlayer(media);
                            itemPickupSound.setVolume(1);
                            itemPickupSound.play();

                        } else {
                            this.setHP(this.getHP() - monster.getDamage());
                            System.out.println(monster.name + " attack Player, Player HP:" + this.HP);
                        }
                    }
                }
        }
    }


    public void Attack(ArrayList<BaseMonster> monsters) {
        Media media = new Media(ClassLoader.getSystemResource("sound/8-bit-laser-151672.mp3").toString());
        MediaPlayer itemPickupSound = new MediaPlayer(media);
        itemPickupSound.setVolume(1);
        itemPickupSound.play();
        setMana(this.getMana()-1);
        if (monsters != null) {
            MonsterSceneLogic logic = MonsterSceneLogic.getInstance();
            logic.addMagic();
            ArrayList<Magic> magicList = logic.getMagicList();
            Iterator<BaseMonster> iterator = monsters.iterator();
            Iterator<Magic> magicIterator = magicList.iterator();
            while (iterator.hasNext()) {
                BaseMonster monster = iterator.next();
                while (magicIterator.hasNext()){
                    Magic magic = magicIterator.next();
                    System.out.println(magic);
                    if (magic.solidArea != null && magic.solidArea.getBoundsInParent().intersects(monster.solidArea.getBoundsInParent())) {
                        System.out.println(monster.solidArea);
                        System.out.println(magic.solidArea);
                        double dx = monster.x - x;
                        double dy = monster.y - y;
                        magicIterator.remove();
                        MonsterSceneLogic.getInstance().getMagicList().remove(magic);
                        RenderableHolder.getInstance().remove((IRenderable) magic);

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
                            dropItem(monster.x, monster.y);
                        } else {
                            monster.setHP(monster.getHP() - this.getDamage());
                            System.out.println(monster.name + " was attacked by Player, HP: " + monster.getHP());
                        }
                    }
                }
            }
        }
    }
    public static void dropItem(double x, double y) {
        Random random = new Random(); // Create a new instance of Random
        // Create an array of BaseItem subclasses
        Class<? extends BaseItem>[] itemClasses = new Class[]{
                healPotion.class, // Example subclass 1
                manaPotion.class, // Example subclass 2
                powerPotion.class
                // Add more subclasses as needed
        };

        // Randomly select a subclass
        Class<? extends BaseItem> randomItemClass = itemClasses[random.nextInt(itemClasses.length)];

        try {
            // Create an instance of the randomly selected subclass
            BaseItem item = randomItemClass.getDeclaredConstructor().newInstance();

            // Set the position of the dropped item
            item.x = x;
            item.y = y;

            // Add the dropped item to your game's rendering system
            RenderableHolder.getInstance().add(item);
            MonsterSceneLogic.getInstance().items.add(item);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any errors that occur during item creation
        }
    }
    public void setUsingShield(boolean usingShield) {
        isUsingShield = usingShield;
        if (usingShield) {
            // Start the shield timer when the shield is activated
            shieldTimer = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
                // Reset the shield flag after 5 seconds
                isUsingShield = false;
            }));
            shieldTimer.play();
        }
    }

    public boolean isUsingShield() {
        return isUsingShield;
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
        this.mana = Math.min(mana,20);
    }

    public int getDamage() {
        return damage;
    }

    public ArrayList<BaseItem> getPlayerItem() {
        return playerItem;
    }

}
