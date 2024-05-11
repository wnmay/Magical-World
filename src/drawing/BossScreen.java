package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.game.BossSceneLogic;
import logic.game.MonsterSceneLogic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class BossScreen extends Canvas {
    public BossScreen(double width, double height){
        super(width, height);

    }


    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if(entity.isVisible()){
                entity.draw(gc);
            }
        }
        drawPlayerHP(gc, BossSceneLogic.getInstance().getPlayer(), 10, 20, 200, 20 );
        drawPlayerMana(gc, BossSceneLogic.getInstance().getPlayer(), 10, 50, 200, 20 );
    }
    public static void drawPlayerHP(GraphicsContext gc, Player player, double x, double y, double width, double height) {
        int maxHP = 20;
        int currentHP = player.getHP();
        double unitWidth = width / maxHP; // Width of each HP unit

        // Draw background for HP stack
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, width, height);

        // Draw remaining HP units
        gc.setFill(Color.LAWNGREEN);
        double remainingWidth = currentHP * unitWidth;
        gc.fillRect(x, y, remainingWidth, height);

        // Draw outline for HP stack
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(x, y, width, height);
    }
    public static void drawPlayerMana(GraphicsContext gc, Player player, double x, double y, double width, double height) {
        int maxMana = 20;
        int currentMana = player.getMana();
        double unitWidth = width / maxMana; // Width of each HP unit

        // Draw background for HP stack
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, width, height);

        // Draw remaining HP units
        gc.setFill(Color.SKYBLUE);
        double remainingWidth = currentMana * unitWidth;
        gc.fillRect(x, y, remainingWidth, height);

        // Draw outline for HP stack
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(x, y, width, height);
    }
}
