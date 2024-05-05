package logic.map;

import input.Input;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Door extends Pane implements IRenderable {
    public Door(){
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
        gc.drawImage(RenderableHolder.door,400,550,50,50);
    }

}
