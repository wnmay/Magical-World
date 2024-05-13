package logic.player;

import utils.Input;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.Entity;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import logic.item.BaseItem;
import logic.item.Key;
import logic.item.potion.healPotion;
import logic.item.potion.manaPotion;
import logic.item.potion.powerPotion;
import logic.item.weapon.Shield;
import logic.item.weapon.Wand;
import logic.map.Door;
import logic.monsters.BaseMonster;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Player extends Entity {
    private double velocityX;
    private double velocityY;
    private boolean weapon;
    private double x,y;
    private static final int WALK = 1;
    private int speed;
    public Rectangle solidArea;
    private WalkState walkState;
    public ArrayList<BaseItem> playerItem = new ArrayList<BaseItem>();
    private final int MAX_HP = 20;
    private final int MAX_MANA = 20;
    private int HP;
    private int mana;
    private int damage;
    private boolean gameOver = false;
    private boolean isUsingShield = false; // Flag to indicate whether the player is currently using the shield item
    private Timeline shieldTimer;
    private Timeline attackedTimer;
    private boolean canBeAttacked;// Timeline for shield duration

    public Player(){
        setSpeed(3);
        setWalkState(WalkState.DOWN);
        setHP(20);
        setMana(20);
        this.damage = 2;
        setCanBeAttacked(true);
    }

    public void moveUpward(){
        if(this.y >= 0){
            this.y -= WALK * speed;
        }
    }
    public void moveDownward(){
        if(this.y <= Config.sceneHeight - Config.playerHeight){
            this.y += WALK * speed;
        }
    }
    public void moveLeft(){
        if(this.x >= 0){
            this.x -= WALK * speed;
        }
    }
    public void moveRight(){
        if(this.x <= Config.sceneWidth - Config.playerWidth){
            this.x += WALK * speed;
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

    public void setSpeed(int speed) {
        this.speed = Math.max(speed, 0);
    }

    public WalkState getWalkState() {
        return walkState;
    }

    public void setWalkState(WalkState walkState) {
        this.walkState = walkState;
    }

    public boolean hasWeapon() {
        return weapon;
    }

    public void checkWeapon() {
        for(BaseItem item:playerItem){
            if(item instanceof Wand){
                weapon = true;
                break;
            }
            else {
                weapon = false;
            }
        }
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
        Iterator<BaseItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            BaseItem item = iterator.next();
            if (item != null && item.solidArea != null && solidArea.intersects(item.solidArea.getBoundsInLocal())) {
                pickUpItem(item);
                iterator.remove(); // Use iterator to safely remove the item
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
                if (item instanceof Key) {
                    getPlayerItem().remove(item);
                    return true; // Player has the key, allow exit
                }
            }
        }
        return false; // Player does not have the key, cannot exit
    }
    public void pickUpItem(BaseItem item) {
        playerItem.add(item);
        System.out.println("Player picked up: " + item.name);
        Media media = new Media(ClassLoader.getSystemResource("sound/game-start-6104.mp3").toString());
        MediaPlayer itemPickupSound = new MediaPlayer(media);
        itemPickupSound.setVolume(1);
        itemPickupSound.play();

    }
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void playerDie () {
        if(this.getHP() <= 0){
            this.gameOver = true;
            Media media = new Media(ClassLoader.getSystemResource("sound/dead-8bit-41400.mp3").toString());
            MediaPlayer itemPickupSound = new MediaPlayer(media);
            itemPickupSound.setVolume(1);
            itemPickupSound.play();
        }
    }

    public void getAttacked(ArrayList<BaseMonster> monsters) {
        for (BaseMonster monster : monsters) {
            if (monster.solidArea != null && solidArea.getBoundsInParent().intersects(monster.solidArea.getBoundsInParent())) {
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
                //check whether player can be attacked
                if (!isUsingShield() && canBeAttacked) {
                        this.setHP(this.getHP() - monster.getDamage());
                        setCanBeAttacked(false);
                        coolDownDuration();
                        System.out.println(monster.name + " attack Player, Player HP:" + this.HP);
                }
            }
        }
    }

    public void Attack(ArrayList<BaseMonster> monsters) {
        Media media = new Media(ClassLoader.getSystemResource("sound/8-bit-laser-151672.mp3").toString());
        MediaPlayer itemPickupSound = new MediaPlayer(media);
        itemPickupSound.setVolume(1);
        itemPickupSound.play();
        setMana(this.getMana() - 1);
        if (monsters != null) {
            MonsterSceneLogic logic = MonsterSceneLogic.getInstance();
            logic.addMagic();
        }
    }
    public void checkMagicCollisionMonster(ArrayList<BaseMonster> monsters) {
        MonsterSceneLogic logic = MonsterSceneLogic.getInstance();
        ArrayList<Magic> magicList = logic.getMagicList();
        Iterator<Magic> magicIterator = magicList.iterator();
        while (magicIterator.hasNext()) {
            Magic magic = magicIterator.next();
            magic.update();
            Iterator<BaseMonster> monsterIterator = monsters.iterator(); // Reset monster iterator for each magic spell
            while (monsterIterator.hasNext()) {
                BaseMonster monster = monsterIterator.next();
                if (magic.solidArea != null && monster.solidArea != null && magic.solidArea.getBoundsInParent().intersects(monster.solidArea.getBoundsInParent())) {
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
                        monsterIterator.remove(); // Remove the monster
                        RenderableHolder.getInstance().remove((IRenderable) monster);
                        System.out.println(monster.name + " died");
                        if( playerItem.size() + MonsterSceneLogic.getInstance().getItems().size() <= 9) {
                            dropItem(monster.x, monster.y);
                        }
                    } else {
                        monster.setHP(monster.getHP() - this.getDamage());
                        System.out.println(monster.name + " was attacked by Player, HP: " + monster.getHP());
                    }
                    break; // Exit the loop after removing the monster
                }
            }
        }
    }

    public static void dropItem(double x, double y) {
        Random random = new Random(); // Create a new instance of Random
        // Create an array of BaseItem subclasses
        Class<? extends BaseItem>[] itemClasses = new Class[]{
                healPotion.class,
                manaPotion.class,
                powerPotion.class,
                Shield.class
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
            MonsterSceneLogic.getInstance().getItems().add(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCanBeAttacked() {
        return canBeAttacked;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    //cool down of player can be attacked
    public void coolDownDuration() {
        if(!canBeAttacked){
            attackedTimer = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
                canBeAttacked = true;
            }));
            attackedTimer.play();
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

    public int getMAX_HP() {
        return MAX_HP;
    }

    public int getMAX_MANA() {
        return MAX_MANA;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = Math.min(HP, 20);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.min(mana, 20);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public ArrayList<BaseItem> getPlayerItem() {
        return playerItem;
    }

}
