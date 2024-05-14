package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Entity;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Wall extends Entity {
    private Image wall = new Image(ClassLoader.getSystemResource("map/wall.png").toString());

    @Override
    public int getZ(){
        return 10;
    }
    @Override
    public void draw(GraphicsContext gc){
        for ( int x = 0 ; x <= 8 ; x ++ ){
            gc.drawImage(wall,x * 100,550,100,50);
        }
    }
}
