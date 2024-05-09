package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;

public class InventorySlot implements IRenderable {
    private boolean visible;
    public InventorySlot() {
        visible = false;
    }
    @Override
    public int getZ() {
        return 11;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        for(int x = 690; x >= 490 ; x -= 50 ) {
            gc.fillRoundRect(x, 10, 40, 40, 10, 10);
        }
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    @Override
    public boolean isVisible() {
        return visible;
    };
//    private void displayPlayerItems(ArrayList<BaseItem> items) {
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//        // Set up font for item display
//        gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//        gc.setTextAlign(TextAlignment.LEFT);
//        gc.setFill(Color.WHITE);
//
//        // Display each item in the player's inventory
//        double y = 20; // Starting y-coordinate for item display
//        for (BaseItem item : items) {
//            gc.fillText(item.name, 10, y);
//            y += 20; // Increment y-coordinate for next item
//        }
//    }
}
