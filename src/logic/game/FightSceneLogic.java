package logic.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import logic.item.BaseItem;
import logic.map.InventorySlot;
import logic.map.MonsterMap;
import logic.monsters.BaseMonster;
import logic.player.Magic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class FightSceneLogic extends BaseSceneLogic{
    protected ArrayList<BaseMonster> monsters;
    protected InventorySlot inventorySlot;
    protected ArrayList<Magic> magicList;
    protected ArrayList<BaseItem> items;
    protected Player player;
    protected List<IRenderable> objectContainer;

    public FightSceneLogic() {
        super();
        monsters = new ArrayList<>();
        magicList = new ArrayList<>();
        items = new ArrayList<>();
        MonsterMap map = new MonsterMap();
        RenderableHolder.getInstance().add(map);
        player = ItemSceneLogic.getInstance().getPlayer();
        items = ItemSceneLogic.getInstance().items;
        addElement(player);

        // Inventory setup
        RenderableHolder.getInstance().add(chest);
        inventorySlot = ItemSceneLogic.getInstance().getInventorySlot();
        inventorySlot.setVisible(false);
        RenderableHolder.getInstance().add(inventorySlot);

        // Start mana and HP regeneration
        startManaRegeneration(player);
        startHpRegeneration(player);
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
    public void logicUpdate() {
        player.update();
        for (BaseMonster monster : monsters) {
            monster.update();
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
        player.checkCollisionItem(items);
        player.checkMagicCollisionMonster(monsters);
    }
    protected void addMonster(BaseMonster monster) {
        monsters.add(monster);
    }

    public ArrayList<BaseMonster> getMonsters() {
        return monsters;
    }

    public ArrayList<Magic> getMagicList() {
        return magicList;
    }

    public ArrayList<BaseItem> getItems() {
        return items;
    }

    public Player getPlayer() {
        return player;
    }
    public abstract void reset();


}
