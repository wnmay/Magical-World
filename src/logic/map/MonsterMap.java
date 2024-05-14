package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Entity;
import sharedObject.RenderableHolder;

public class MonsterMap extends Entity {
    private Image grass = new Image(ClassLoader.getSystemResource("map/grass.png").toString());
    private Image flower = new Image(ClassLoader.getSystemResource("map/flower.png").toString());
    @Override
    public int getZ(){
        return -100;
    }
    @Override
    public void draw(GraphicsContext gc){
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 3; y++) {
                gc.drawImage(grass,x * 200,y * 200,200,200);
            }
        }
        gc.drawImage(flower, 40, 100, 70, 70);
        gc.drawImage(flower, 700, 400, 70, 70);
        gc.drawImage(flower, 320, 250, 70, 70);
        gc.drawImage(flower, 100, 500, 70, 70);
        gc.drawImage(flower, 650, 20, 70, 70);

    }
}
