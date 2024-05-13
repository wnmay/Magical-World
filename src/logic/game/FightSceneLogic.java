package logic.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import logic.monsters.BaseMonster;
import logic.player.Player;

import java.util.ArrayList;

public abstract class FightSceneLogic extends BaseSceneLogic{
    public FightSceneLogic() {
        super();
    }
    public abstract void logicUpdate();
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

}
