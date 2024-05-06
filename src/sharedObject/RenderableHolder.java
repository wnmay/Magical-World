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
    public static Image wand;
    public static Image healPotion;
    public static Image powerPotion;
    public static Image manaPotion;
    public static Image door;
    public static Image wall;
    public static Image tree;
    public static Image rock;
    public static Image chest;


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
        wand = new Image(ClassLoader.getSystemResource("weapon/Wand.gif").toString());
        healPotion = new Image(ClassLoader.getSystemResource("potion/HealPotion.gif").toString());
        manaPotion = new Image(ClassLoader.getSystemResource("potion/ManaPotion.gif").toString());
        powerPotion = new Image(ClassLoader.getSystemResource("potion/PowerPotion.gif").toString());
        door = new Image(ClassLoader.getSystemResource("map/door.png").toString());
        wall = new Image(ClassLoader.getSystemResource("map/wall.png").toString());
        tree = new Image(ClassLoader.getSystemResource("map/tree.png").toString());
        rock = new Image(ClassLoader.getSystemResource("map/rock.png").toString());
        chest = new Image(ClassLoader.getSystemResource("map/chest.png").toString());

    }

    public void add(IRenderable entity){
        entities.add(entity);
        Collections.sort(entities, comparator);
    }
    public void remove(IRenderable entity){
        entities.remove(entity);
    }

    public List<IRenderable> getEntities(){
        return entities;
    }

}
