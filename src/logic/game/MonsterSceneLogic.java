package logic.game;

import logic.map.*;
import logic.monsters.BaseMonster;
import logic.monsters.Bat;
import logic.monsters.Golem;
import logic.player.Player;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.List;

public class MonsterSceneLogic {
    private static MonsterSceneLogic instance;
    private List<IRenderable> objectContainer;
    private ArrayList<BaseMonster> monsters;

    private Player player;
    private Chest chest;
    private Bat bat;
    private Golem golem;
    public MonsterSceneLogic() {
        this.objectContainer = new ArrayList<IRenderable>();
        this.monsters = new ArrayList<BaseMonster>();
        MonsterMap map=new MonsterMap();
        RenderableHolder.getInstance().add(map);
        player = ItemSceneLogic.getInstance().getPlayer();
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
        addElement(bat); addMonster(bat);
        golem = new Golem(100,200,1,player);
        addElement(golem); addMonster(golem);



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

    public void logicUpdate(){
        player.update();
        chest.CheckChestClick(player.getPlayerItem());
        bat.update();
        golem.update();
//        player.checkCollisionMonster(this.monsters);
        player.getAttacked(monsters);
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
