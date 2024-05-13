package logic.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import logic.map.*;
import logic.monsters.Bat;
import logic.monsters.Golem;
import logic.player.Magic;

public class MonsterSceneLogic extends FightSceneLogic{
    private static MonsterSceneLogic instance;
    private int generatedMonsterCount;
    private Bat bat;
    private Golem golem;

    public MonsterSceneLogic() {
        super();
        //monster
        bat = new Bat(2, player);
        addElement(bat); addMonster(bat);
        golem = new Golem(1,player);
        addElement(golem); addMonster(golem);
        generatedMonsterCount = 0;
        startMonsterGeneration();

    }
    private void startMonsterGeneration() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (generatedMonsterCount < 5) {
                generateRandomMonster();
                generatedMonsterCount ++;
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

    public void addMagic() {
        Magic magic = new Magic();
        magic.setMagicState(getPlayer().getWalkState());
        addElement(magic);
        magicList.add(magic);
    }

    public InventorySlot getInventorySlot() {
        return inventorySlot;
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
