package logic;


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
    }

    protected void addElement(IRenderable element){
        objectContainer.add(element);
        RenderableHolder.getInstance().add(element);
    }

    public void logicUpdate(){
        player.update();
    }
}
