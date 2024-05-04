package sharedObject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance=new RenderableHolder();
    private List<IRenderable> entities;
    public static Image grassSprite;

    static {
        loadResource();
    }

    public RenderableHolder(){
        entities = new ArrayList<IRenderable>();
    }

    public static RenderableHolder getInstance(){
        return instance;
    }

    public static void loadResource(){
        grassSprite=new Image(ClassLoader.getSystemResource("grass.png").toString());
    }

    public void add(IRenderable entity){
        entities.add(entity);
    }
    public List<IRenderable> getEntities(){
        return entities;
    }

}
