package logic.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import logic.item.BaseItem;
import logic.map.*;
import logic.monsters.BaseMonster;
import logic.monsters.Bat;
import logic.monsters.Golem;
import logic.player.Magic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static logic.player.Player.dropItem;

public class MonsterSceneLogic extends FightSceneLogic{
    private static MonsterSceneLogic instance;
    private ArrayList<BaseMonster> monsters;
    private InventorySlot inventorySlot;
    private ArrayList<Magic> magicList;
    public ArrayList<BaseItem> items;
    private int generatedMonsterCount;
    private Player player;
    private Chest chest;
    private Bat bat;
    private Golem golem;

    public MonsterSceneLogic() {
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
        bat = new Bat(2, player);
        addElement(bat); addMonster(bat);
        golem = new Golem(1,player);
        addElement(golem); addMonster(golem);
        generatedMonsterCount = 0;
        startMonsterGeneration();
        startManaRegeneration(player);
        startHpRegeneration(player);

    }
    private void startMonsterGeneration() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            if (generatedMonsterCount < 5) {
                generateRandomMonster();
                generatedMonsterCount++;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }

    private void generateRandomMonster() {
        if (!player.isGameOver()) {
            // Generate a random number to determine the type of monster
            int random = (int) (Math.random() * 2); // 0 or 1

            // Create a random monster based on the random number
            if (random == 0) {
                Bat bat = new Bat(2, player);
                addElement(bat);
                addMonster(bat);
                Bat bat1 = new Bat(2, player);
                addElement(bat1);
                addMonster(bat1);
            } else {
                Golem golem = new Golem(1, player);
                addElement(golem);
                addMonster(golem);
            }
        }
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

    public void addMagic() {
        Magic magic = new Magic();
        magic.setMagicState(getPlayer().getWalkState());
        addElement(magic);
        magicList.add(magic);
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

    public List<IRenderable> getObjectContainer() {
        return objectContainer;
    }

    public void logicUpdate() {
        player.update();
        for (BaseMonster monster : monsters) {
            monster.update(); // Call the move method of the bat
        }
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
        player.checkMagicCollisionMonster(monsters);

        // Check player collision with items after iterating over magicList
        player.checkCollisionItem(items);
    }

    public static MonsterSceneLogic getInstance() {
        if (instance == null) {
            instance = new MonsterSceneLogic();
        }
        return instance;
    }
    public void reset () {
        instance = null;
    }

    public int getGeneratedMonsterCount() {
        return generatedMonsterCount;
    }
}
