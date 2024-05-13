package logic.map;

import javafx.scene.canvas.GraphicsContext;
import logic.Entity;
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
        for (int x = 0 ; x <= 10 ; x++){
            gc.drawImage(RenderableHolder.bookShelf,x * 80,0,80,80);
        }
        gc.drawImage(RenderableHolder.window,329,-11,105,105);
        gc.drawImage(RenderableHolder.window,425,-11,105,105);
    }

}
