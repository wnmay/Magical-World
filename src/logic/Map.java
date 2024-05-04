package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Map implements IRenderable {
    @Override
    public int getZ(){
        return -100;
    }
    @Override
    public void draw(GraphicsContext gc){
        for (int x = 0; x<=4; x++) {
            for (int y = 0; y <= 3; y++) {
                gc.drawImage(RenderableHolder.grassSprite,x*200,y*200,200,200);
            }
        }
    }

}
