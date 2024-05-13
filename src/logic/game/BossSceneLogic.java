package logic.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import logic.item.BaseItem;
import logic.map.Chest;
import logic.map.InventorySlot;
import logic.map.MonsterMap;
import logic.monsters.*;
import logic.player.Magic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BossSceneLogic extends FightSceneLogic{
    private static BossSceneLogic instance;
    private ArrayList<BaseMonster> monsters;
    private InventorySlot inventorySlot;
    private ArrayList<Magic> magicList;
    public ArrayList<BaseItem> items;
    private FireBomb fireBomb;
    private Player player;
    private Chest chest;
    private Boss boss;
    public BossSceneLogic() {
        super();
        objectContainer.clear();
        this.monsters = new ArrayList<BaseMonster>();
        this.magicList = new ArrayList<Magic>();
        MonsterMap map=new MonsterMap();
        RenderableHolder.getInstance().add(map);
        player = ItemSceneLogic.getInstance().getPlayer();
        items = ItemSceneLogic.getInstance().items;
        addElement(player);

        //chest
        chest = ItemSceneLogic.getInstance().getChest();
        RenderableHolder.getInstance().add(chest);
        inventorySlot = ItemSceneLogic.getInstance().getInventorySlot();
        inventorySlot.setVisible(false);
        RenderableHolder.getInstance().add(inventorySlot);

        //monster
        boss = new Boss(400,300,1,player);
        addElement(boss);
        addMonster(boss);
        fireBombLoop();
        startManaRegeneration(player);
        startHpRegeneration(player);

    }
    private void fireBombLoop() {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                if(!player.isGameOver()){
                    fireBomb = new FireBomb(player);
                    addElement(fireBomb);
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
    }
    public void startManaRegeneration(Player player) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
        // Increment the player's mana by 1 every 5 seconds
        if (!player.isGameOver()) {
            player.setMana(player.getMana() + 1);
        }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
    timeline.play(); // Start the timeline
    }
    public void startHpRegeneration(Player player) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            // Increment the player's mana by 1 every 5 seconds
            if (!player.isGameOver()) {
                player.setHP(player.getHP() + 1);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }

    public ArrayList<Magic> getMagicList() {
        return magicList;
    }

    public InventorySlot getInventorySlot() {
        return inventorySlot;
    }

    public Chest getChest() {
        return chest;
    }

    public Player getPlayer() {
        return player;
    }

    protected void addMonster(BaseMonster monster){
        monsters.add(monster);
    }

    public ArrayList<BaseMonster> getMonsters() {
        return monsters;
    }

    public void logicUpdate() {
        player.update();
        boss.update();
        player.getAttacked(monsters);
        player.playerDie();

        // Use iterator to safely remove magic elements while iterating
        Iterator<Magic> iterator = magicList.iterator();
        while (iterator.hasNext()) {
            Magic mg = iterator.next();
            mg.update();
            // Check if magic is out of bounds
            if (!(mg.getX() <= Config.sceneWidth - Config.playerWidth && mg.getX() >= 0) ||
                    !(mg.getY() <= Config.sceneHeight - Config.playerHeight && mg.getY() >= 0)) {
                // Remove magic from the list and renderable holder
                iterator.remove();
                RenderableHolder.getInstance().remove(mg);
            }
        }

        //fireball attacking
        if(fireBomb != null){
            if(fireBomb.isVisible() && !player.isGameOver()){
                if(player.isCanBeAttacked() && fireBomb.solidArea.getBoundsInParent().intersects(player.solidArea.getBoundsInParent())){
                    player.setHP(player.getHP()-2);
                    player.setCanBeAttacked(false);
                    player.coolDownDuration();
                }
            }
            else {
                RenderableHolder.getInstance().remove(fireBomb);
            }
        }

        player.checkMagicCollisionMonster(monsters);

        // Check player collision with items after iterating over magicList
        player.checkCollisionItem(items);
    }

    public static BossSceneLogic getInstance() {
        if (instance == null) {
            instance = new BossSceneLogic();
        }
        return instance;
    }
    public void reset () {
        instance = null;
    }

}
