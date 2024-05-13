package logic.game;

import java.util.ArrayList;
import java.util.List;
import logic.map.Chest;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public abstract class BaseSceneLogic {
    protected List<IRenderable> objectContainer;
    protected Chest chest;
    public BaseSceneLogic() {
        this.objectContainer = new ArrayList<IRenderable>();
        this.chest = new Chest();
    }
    protected void addElement(IRenderable element) {
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }
    public abstract void logicUpdate();

    public Chest getChest() {
        return chest;
    }
}
