package logic.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.game.ItemSceneLogic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Broom extends BaseItem implements IRenderable {
    private boolean used;
    private boolean drawn = false;
    private Image broom = new Image(ClassLoader.getSystemResource("item/Broom.gif").toString());;


    public Broom() {
        super("broom");
        used = false;
    }

    @Override
    public int getZ() {
        return 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        if (!drawn) {
            // Generate random initial position only once
            this.x = (int) (Math.random() * (700));
            this.y = (int) (Math.random() * (450 - 80 + 1)) + 80;
            drawn = true;
        }

        // Draw the image at the initial position
        gc.drawImage(broom, x, y, 80, 80);
        solidArea = new Rectangle(x, y, 80, 80);
    }
    public Image getImage() {
        return broom;
    }

    @Override
    public void useItem() {
        Player player = ItemSceneLogic.getInstance().getPlayer();
        player.setSpeed(5);
        used = true;
        System.out.println("broom is used");
    }


    public boolean isUsed() {
        return used;
    }
}

