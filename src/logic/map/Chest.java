package logic.map;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.Entity;
import logic.item.BaseItem;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import java.util.ArrayList;

public class Chest extends Entity {
    private Canvas canvas;

    private Rectangle solidArea;

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

        gc.drawImage(RenderableHolder.chest,740,0,50,50);
        solidArea = new Rectangle(740,0,50,50);
    }
}
