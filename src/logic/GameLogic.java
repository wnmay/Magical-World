package logic;


import logic.item.weapon.wand;
import logic.item.potion.*;
import logic.map.Map;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private List<IRenderable> objectContainer;

    private Player player;

    public GameLogic(){
        this.objectContainer = new ArrayList<IRenderable>();
        Map map=new Map();
        RenderableHolder.getInstance().add(map);
        player=new Player(200,200);
        addElement(player);
        wand wand =new wand();
        RenderableHolder.getInstance().add(wand);
        powerPotion powerPotion = new powerPotion();
        RenderableHolder.getInstance().add(powerPotion);
        healPotion healPotion = new healPotion();
        RenderableHolder.getInstance().add(healPotion);
        manaPotion manaPotion = new manaPotion();
        RenderableHolder.getInstance().add(manaPotion);




    }

    protected void addElement(IRenderable element){
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }

    public void logicUpdate(){
        player.update();
    }
}
