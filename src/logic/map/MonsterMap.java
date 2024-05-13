package logic.map;

import javafx.scene.canvas.GraphicsContext;
import logic.Entity;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MonsterMap extends Entity {
    @Override
    public int getZ(){
        return -100;
    }
    @Override
    public void draw(GraphicsContext gc){
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 3; y++) {
                gc.drawImage(RenderableHolder.grassSprite,x * 200,y * 200,200,200);
            }
        }
        gc.drawImage(RenderableHolder.flower, 40, 100, 70, 70);
        gc.drawImage(RenderableHolder.flower, 700, 400, 70, 70);
        gc.drawImage(RenderableHolder.flower, 320, 250, 70, 70);
        gc.drawImage(RenderableHolder.flower, 100, 500, 70, 70);
        gc.drawImage(RenderableHolder.flower, 650, 20, 70, 70);

    }
}
