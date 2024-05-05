package sharedObject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance=new RenderableHolder();
    private List<IRenderable> entities;
    private Comparator<IRenderable> comparator;
    public static Image grassSprite;
    public static Image playerFront;
    public static Image playerBack;
    public static Image playerLeft;
    public static Image playerRight;

    static {
        loadResource();
    }

    public RenderableHolder(){
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if (o1.getZ() > o2.getZ())
                return 1;
            return -1;
        };
    }

    public static RenderableHolder getInstance(){
        return instance;
    }

    public static void loadResource(){
        grassSprite=new Image(ClassLoader.getSystemResource("map/grass.png").toString());
        playerFront = new Image(ClassLoader.getSystemResource("player/playerFront.png").toString());
        playerBack = new Image(ClassLoader.getSystemResource("player/playerBack.png").toString());
        playerLeft = new Image(ClassLoader.getSystemResource("player/playerLeft.png").toString());
        playerRight = new Image(ClassLoader.getSystemResource("player/playerRight.png").toString());
    }

    public void add(IRenderable entity){
        entities.add(entity);
        Collections.sort(entities, comparator);
    }
    public List<IRenderable> getEntities(){
        return entities;
    }

}
