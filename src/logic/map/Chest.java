package logic.map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import sharedObject.RenderableHolder;

public class Chest extends Entity {
    private Rectangle solidArea;
    private Image chest = new Image(ClassLoader.getSystemResource("map/chest.png").toString());;

    public Chest() {

    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    @Override
    public int getZ(){
        return 11;
    }

    @Override
    public void draw(GraphicsContext gc){

        gc.drawImage(chest,740,0,50,50);
        solidArea = new Rectangle(740,0,50,50);
    }
}
