package logic;


import logic.item.BaseItem;
import logic.item.weapon.wand;
import logic.item.potion.*;
import logic.map.Door;
import logic.map.Map;
import logic.map.Wall;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<IRenderable> objectContainer;
    public ArrayList<BaseItem> items;
    private Player player;

    public GameLogic(){
        this.objectContainer = new ArrayList<IRenderable>();
        this.items = new ArrayList<BaseItem>();
        Map map=new Map();
        RenderableHolder.getInstance().add(map);
        player=new Player(200,200);
        addElement(player);
        wand wand =new wand();
        addElement(wand); addItem(wand);

        Door door = new Door();
        RenderableHolder.getInstance().add(door);
        Wall wall = new Wall();
        RenderableHolder.getInstance().add(wall);

        powerPotion powerPotion = new powerPotion();
        addElement(powerPotion); addItem(powerPotion);
        healPotion healPotion = new healPotion();
        addElement(healPotion); addItem(healPotion);
        manaPotion manaPotion = new manaPotion();
        addElement(manaPotion); addItem(manaPotion);




    }

    protected void addElement(IRenderable element){
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }

    protected void addItem(BaseItem item){
        items.add(item);
    }

    public void logicUpdate(){
        player.update();
        player.checkCollision(items);
    }
}
