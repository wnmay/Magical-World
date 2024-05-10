package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.game.ItemSceneLogic;
import logic.game.MonsterSceneLogic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.paint.Color;

import java.awt.*;

public class MonsterScreen extends Canvas {
    public MonsterScreen(double width, double height){
        super(width, height);

    }


    public void paintComponent() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if(entity.isVisible()){
                entity.draw(gc);
            }
        }
        drawPlayerHP(gc, MonsterSceneLogic.getInstance().getPlayer(), 10, 20, 150, 20 );
    }
    public static void drawPlayerHP(GraphicsContext gc, Player player, double x, double y, double width, double height) {
        int maxHP = 20;
        int currentHP = player.getHP();
        double unitWidth = width / maxHP; // Width of each HP unit

        // Draw background for HP stack
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, width, height);

        // Draw remaining HP units
        gc.setFill(Color.GREEN);
        double remainingWidth = currentHP * unitWidth;
        gc.fillRect(x, y, remainingWidth, height);

        // Draw outline for HP stack
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(x, y, width, height);
    }

}
