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
        gc.setFill(Color.BURLYWOOD);
        gc.fillRoundRect(230, 5, 510, 55, 10, 10);
        for(double x = 690 ; x >= 240 ; x -= 50){
            gc.setFill(Color.WHITE);
            gc.fillRoundRect(x, 10, 40, 40, 10, 10);
            Rectangle area = new Rectangle(x,10,40,40);
            slotAreaList.add(area);
        }
        if(!ItemSceneLogic.getInstance().getPlayer().getPlayerItem().isEmpty()) {
            int x = 690;
            for(BaseItem item: ItemSceneLogic.getInstance().getPlayer().getPlayerItem()){
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

}
