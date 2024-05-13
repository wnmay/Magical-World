package logic.game;

import java.util.ArrayList;
import java.util.List;

import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public abstract class BaseSceneLogic {
    protected List<IRenderable> objectContainer;

    public BaseSceneLogic() {
        this.objectContainer = new ArrayList<IRenderable>();
    }
    protected void addElement(IRenderable element) {
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }
    public abstract void logicUpdate();

}
