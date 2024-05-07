package logic.game;

import logic.item.BaseItem;
import logic.item.potion.healPotion;
import logic.item.potion.manaPotion;
import logic.item.potion.powerPotion;
import logic.item.weapon.wand;
import logic.map.*;
import logic.monsters.Bat;
import logic.monsters.Golem;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.List;

import static sharedObject.RenderableHolder.bat;

public class FightSceneLogic {

    private List<IRenderable> objectContainer;
    public ArrayList<BaseItem> items;
    private Player player;
    private Chest chest;
    private Bat bat;
    private Golem golem;
    public FightSceneLogic() {
        this.objectContainer = new ArrayList<IRenderable>();
        Map map=new Map();
        RenderableHolder.getInstance().add(map);
        player = new Player(400,300);
        addElement(player);


        //map
        RenderableHolder.getInstance().add(Door.getInstance());
        Wall wall = new Wall();
        RenderableHolder.getInstance().add(wall);

//        Rock rock = new Rock();
//        RenderableHolder.getInstance().add(rock);
        chest = new Chest();
        RenderableHolder.getInstance().add(chest);
        bat = new Bat(10,10,2, player);
        addElement(bat);
        golem = new Golem(100,200,1,player);
        addElement(golem);



    }
    public Player getPlayer() {
        return player;
    }

    protected void addElement(IRenderable element){
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }


    public void logicUpdate(){
        player.update();
        chest.CheckChestClick(player.getPlayerItem());
        bat.update();
        golem.update();
    }
    public boolean sceneUpdate() {
        return player.checkExitScene();
    }
}
