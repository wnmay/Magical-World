package logic.map;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import logic.item.BaseItem;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import java.util.ArrayList;

public class Chest extends Pane implements IRenderable {
    private Canvas canvas;

    public Chest() {
        canvas = new Canvas(50, 50); // Set the size of the canvas as needed
        getChildren().add(canvas);

    }

    public void CheckChestClick(ArrayList<BaseItem> items){
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // When chest is clicked, display player's items
                displayPlayerItems(items);
                System.out.println("Chest");
            }
        });
    }


    private void displayPlayerItems(ArrayList<BaseItem> items) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set up font for item display
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFill(Color.WHITE);

        // Display each item in the player's inventory
        double y = 20; // Starting y-coordinate for item display
        for (BaseItem item : items) {
            gc.fillText(item.name, 10, y);
            y += 20; // Increment y-coordinate for next item
        }
    }

    @Override
    public int getZ(){
        return 11;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.chest,740,0,50,50);
    }
}
