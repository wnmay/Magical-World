package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.Entity;
import sharedObject.RenderableHolder;

public class Door extends Entity {

    private static Door instance;
    private Rectangle doorArea;
    private Image door = new Image(ClassLoader.getSystemResource("map/door.png").toString());

    private final double x;
    private final double y;
    public Door(){
        this.x = 400;
        this.y = 550;
    }
    @Override
    public int getZ(){
        return 11;
    }
    @Override
    public void draw(GraphicsContext gc){

        gc.drawImage(door,x,y,50,50);
        doorArea = new Rectangle(x,y,50,50);

    }

    public static Door getInstance() {
        if(instance == null){
            instance = new Door();
        }
        return instance;
    }

    public Rectangle getDoorArea() {
        return doorArea;
    }
}
