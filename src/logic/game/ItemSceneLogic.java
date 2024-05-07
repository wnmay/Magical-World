package logic.game;


import logic.item.BaseItem;
import logic.item.weapon.wand;
import logic.item.potion.*;
import logic.map.*;
import logic.monsters.Bat;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


import java.util.ArrayList;
import java.util.List;

public class ItemSceneLogic {
    private List<IRenderable> objectContainer;
    public ArrayList<BaseItem> items;
    private Player player;
    private Chest chest;
    private Bat bat;

    public ItemSceneLogic(){
        this.objectContainer = new ArrayList<IRenderable>();
        this.items = new ArrayList<BaseItem>();
        Map map=new Map();
        RenderableHolder.getInstance().add(map);
        player = new Player(400,300);
        addElement(player);
        wand wand =new wand();
        addElement(wand); addItem(wand);

        //map
        RenderableHolder.getInstance().add(Door.getInstance());
        Wall wall = new Wall();
        RenderableHolder.getInstance().add(wall);
//        Tree tree = new Tree();
//        RenderableHolder.getInstance().add(tree);
        Rock rock = new Rock();
        RenderableHolder.getInstance().add(rock);
        chest = new Chest();
        RenderableHolder.getInstance().add(chest);

        //item
        powerPotion powerPotion = new powerPotion();
        addElement(powerPotion); addItem(powerPotion);
        healPotion healPotion = new healPotion();
        addElement(healPotion); addItem(healPotion);
        manaPotion manaPotion = new manaPotion();
        addElement(manaPotion); addItem(manaPotion);



    }

    public Player getPlayer() {
        return player;
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
        chest.CheckChestClick(player.getPlayerItem());



    }
    public boolean sceneUpdate() {
        return player.checkExitScene();
    }
}
