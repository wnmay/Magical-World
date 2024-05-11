package logic.game;

import logic.item.BaseItem;
import logic.map.*;
import logic.monsters.BaseMonster;
import logic.monsters.Bat;
import logic.monsters.Golem;
import logic.player.Magic;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.List;

public class MonsterSceneLogic {
    private static MonsterSceneLogic instance;
    private List<IRenderable> objectContainer;
    private ArrayList<BaseMonster> monsters;
    private InventorySlot inventorySlot;
    private ArrayList<Magic> magicList;
    public ArrayList<BaseItem> items;

    private Player player;
    private Chest chest;
    private Bat bat;
    private Golem golem;
    public MonsterSceneLogic() {
        this.objectContainer = new ArrayList<IRenderable>();
        this.monsters = new ArrayList<BaseMonster>();
        this.magicList = new ArrayList<Magic>();
        MonsterMap map=new MonsterMap();
        RenderableHolder.getInstance().add(map);
        player = ItemSceneLogic.getInstance().getPlayer();
        items = ItemSceneLogic.getInstance().items;
        addElement(player);


        //map

//        Rock rock = new Rock();
//        RenderableHolder.getInstance().add(rock);

        //chest
        chest = ItemSceneLogic.getInstance().getChest();
        RenderableHolder.getInstance().add(chest);
        inventorySlot = ItemSceneLogic.getInstance().getInventorySlot();
        inventorySlot.setVisible(false);
        RenderableHolder.getInstance().add(inventorySlot);

        //monster
        bat = new Bat(10,10,2, player);
        addElement(bat); addMonster(bat);
        golem = new Golem(100,200,1,player);
        addElement(golem); addMonster(golem);



    }


    public void addMagic() {
        Magic magic = new Magic();
        magic.setMagicState(getPlayer().getWalkState());
        addElement(magic);
        magicList.add(magic);
    }

    public ArrayList<Magic> getMagicList() {
        return magicList;
    }

    public InventorySlot getInventorySlot() {
        return inventorySlot;
    }

    public Chest getChest() {
        return chest;
    }

    public Player getPlayer() {
        return player;
    }

    protected void addElement(IRenderable element){
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }
    protected void addMonster(BaseMonster monster){
        monsters.add(monster);
    }

    public ArrayList<BaseMonster> getMonsters() {
        return monsters;
    }

    public void logicUpdate(){
        player.update();
//        chest.CheckChestClick(player.getPlayerItem());
        bat.update();
        golem.update();
//        player.checkCollisionMonster(this.monsters);
        player.getAttacked(monsters);
        if(!magicList.isEmpty()){
            for(Magic mg:magicList){
                mg.update();
            }
        }
        player.checkCollisionItem(items);
    }
    public boolean sceneUpdate() {
        return player.checkExitScene();
    }

    public static MonsterSceneLogic getInstance() {
        if (instance == null) {
            instance = new MonsterSceneLogic();
        }
        return instance;
    }
    public void reset () {
        instance = null;
    }
}
