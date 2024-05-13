package logic.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import logic.map.InventorySlot;
import logic.monsters.*;
import sharedObject.RenderableHolder;

public class BossSceneLogic extends FightSceneLogic{
    private static BossSceneLogic instance;
    private FireBomb fireBomb;
    private Boss boss;
    public BossSceneLogic() {
        super();

        //monster
        boss = new Boss(400,300,1,player);
        addElement(boss);
        addMonster(boss);
        fireBombLoop();
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
    public InventorySlot getInventorySlot() {
        return inventorySlot;
    }

    public void logicUpdate() {
        super.logicUpdate();
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
        player.checkCollisionItem(getItems());
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
