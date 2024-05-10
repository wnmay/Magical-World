package logic.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.game.ItemSceneLogic;
import logic.item.BaseItem;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.ArrayList;

public class InventorySlot implements IRenderable {
    private ArrayList<Rectangle> slotAreaList;
    private boolean visible;
    public InventorySlot() {
        visible = false;
        slotAreaList = new ArrayList<Rectangle>();
    }
    @Override
    public int getZ() {
        return 11;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(!ItemSceneLogic.getInstance().getPlayer().getPlayerItem().isEmpty()) {
            int x = 690;
            for(BaseItem item: ItemSceneLogic.getInstance().getPlayer().getPlayerItem()){
                gc.setFill(Color.WHITE);
                gc.fillRoundRect(x, 10, 40, 40, 10, 10);
                Rectangle area = new Rectangle(x,10,40,40);
                slotAreaList.add(area);
                gc.drawImage(item.getImage(),x,10,40,40);
                x -= 50;
            }
        }

    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    @Override
    public boolean isVisible() {
        return visible;
    };

    public ArrayList<Rectangle> getSlotAreaList() {
        return slotAreaList;
    }
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
