package drawing;

import javafx.scene.canvas.GraphicsContext;
import logic.game.MonsterSceneLogic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.paint.Color;

public class FightScreen extends GameScreen {
    public FightScreen(double width, double height){
        super(width, height);
    }

    @Override
    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if(entity.isVisible()){
                entity.draw(gc);
            }
        }
        drawPlayerHP(gc, MonsterSceneLogic.getInstance().getPlayer());
        drawPlayerMana(gc, MonsterSceneLogic.getInstance().getPlayer());
    }
    public static void drawPlayerHP(GraphicsContext gc, Player player) {
        int maxHP = player.getMAX_HP();
        int currentHP = player.getHP();
        double unitWidth = 200 / maxHP; // Width of each HP unit

        // Draw background for HP stack
        gc.setFill(Color.BLACK);
        gc.fillRect(10, 20, 200, 20);

        // Draw remaining HP units
        gc.setFill(Color.LAWNGREEN);
        double remainingWidth = currentHP * unitWidth;
        gc.fillRect(10, 20, remainingWidth, 20);

        // Draw outline for HP stack
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(10, 20, 200, 20);
    }
    public static void drawPlayerMana(GraphicsContext gc, Player player) {
        int maxMana = player.getMAX_MANA();
        int currentMana = player.getMana();
        double unitWidth = 200 / maxMana; // Width of each HP unit

        // Draw background for HP stack
        gc.setFill(Color.BLACK);
        gc.fillRect(10, 50, 200, 20);

        // Draw remaining HP units
        gc.setFill(Color.SKYBLUE);
        double remainingWidth = currentMana * unitWidth;
        gc.fillRect(10, 50, remainingWidth, 20);

        // Draw outline for HP stack
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(10, 50, 200, 20);
    }

}
