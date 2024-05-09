package logic.map;

import javafx.scene.canvas.GraphicsContext;
import logic.Entity;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Wall extends Entity {
    @Override
    public int getZ(){
        return 10;
    }
    @Override
    public void draw(GraphicsContext gc){
        for (int x = 0 ; x <= 8 ; x++){
            gc.drawImage(RenderableHolder.wall,x*100,550,100,50);
        }
    }
}
