package logic.map;

import input.Input;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Door extends Pane implements IRenderable {

    private static Door instance;
    private Rectangle doorArea;
    private final double x;
    private final double y;
    public Door(){
        this.x = 400;
        this.y = 550;
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("open door");
            }
        });
    }
    @Override
    public int getZ(){
        return 11;
    }
    @Override
    public void draw(GraphicsContext gc){

        gc.drawImage(RenderableHolder.door,x,y,50,50);
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
