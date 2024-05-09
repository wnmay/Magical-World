package logic.map;

import javafx.scene.canvas.GraphicsContext;
import logic.Entity;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class ItemMap extends Entity {
    @Override
    public int getZ(){
        return -100;
    }
    @Override
    public void draw(GraphicsContext gc){
        for ( int x = 0; x <= 8; x ++ ) {
            for ( int y = 0; y <= 6; y ++ ) {
                gc.drawImage(RenderableHolder.castleSprite, x * 100, y * 100, 100, 100);
            }
        }
    }

}
