package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Entity;
import sharedObject.RenderableHolder;

public class ItemMap extends Entity {
    private Image castleSprite = new Image(ClassLoader.getSystemResource("map/stoneFloor.png").toString());
    private Image bookShelf = new Image(ClassLoader.getSystemResource("map/bookshelf.jpeg").toString());
    private Image window = new Image(ClassLoader.getSystemResource("map/window.png").toString());

    @Override
    public int getZ(){
        return -100;
    }
    @Override
    public void draw(GraphicsContext gc){
        for ( int x = 0; x <= 8; x ++ ) {
            for ( int y = 0; y <= 6; y ++ ) {
                gc.drawImage(castleSprite, x * 100, y * 100, 100, 100);
            }
        }
        for (int x = 0 ; x <= 10 ; x++){
            gc.drawImage(bookShelf,x * 80,0,80,80);
        }
        gc.drawImage(window,329,-11,105,105);
        gc.drawImage(window,425,-11,105,105);
    }

}
