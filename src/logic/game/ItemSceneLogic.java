package logic.game;


import logic.item.BaseItem;
import logic.item.Broom;
import logic.item.Key;
import logic.item.weapon.Shield;
import logic.item.weapon.Wand;
import logic.item.potion.*;
import logic.map.*;
import logic.monsters.Bat;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


import java.util.ArrayList;
import java.util.List;

public class ItemSceneLogic {
    private InventorySlot inventorySlot;
    private static ItemSceneLogic instance;
    private List<IRenderable> objectContainer;
    public ArrayList<BaseItem> items;
    private Player player;
    private Chest chest;
    public boolean isBroomUsed;
    private Broom broom;
    private Bat bat;

    public ItemSceneLogic(){
        this.objectContainer = new ArrayList<IRenderable>();
        this.items = new ArrayList<BaseItem>();
        ItemMap map=new ItemMap();
        RenderableHolder.getInstance().add(map);
        player = new Player();
        player.setPosition(400, 300);
        addElement(player);
        Wand wand =new Wand();
        addElement(wand); addItem(wand);

        //map
        RenderableHolder.getInstance().add(Door.getInstance());
        Wall wall = new Wall();
        RenderableHolder.getInstance().add(wall);

        //chest
        chest = new Chest();
        RenderableHolder.getInstance().add(chest);
        inventorySlot = new InventorySlot();
        RenderableHolder.getInstance().add(inventorySlot);


        //item
        powerPotion powerPotion = new powerPotion();
        addElement(powerPotion); addItem(powerPotion);
        healPotion healPotion = new healPotion();
        addElement(healPotion); addItem(healPotion);
        manaPotion manaPotion = new manaPotion();
        addElement(manaPotion); addItem(manaPotion);
        Key key = new Key();
        addElement(key); addItem(key);
        broom = new Broom();
        addElement(broom); addItem(broom);
        Shield shield = new Shield();
        addElement(shield); addItem(shield);



        //item slot
//        RenderableHolder.getInstance().add();



    }

    public Broom getBroom(){
        return broom;
    }

    public InventorySlot getInventorySlot() {
        return inventorySlot;
    }
    public Chest getChest(){
        return chest;
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
        player.checkCollisionItem(items);
//        chest.CheckChestClick(player.getPlayerItem());

    }
    public boolean sceneUpdate() {
        return player.checkExitScene();
    }

    public static ItemSceneLogic getInstance() {
        if (instance == null) {
            instance = new ItemSceneLogic();
        }
        return instance;
    }

    public void reset() {
        instance = null;
    }

}
