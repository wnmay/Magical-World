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
import utils.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static logic.player.Player.dropItem;

public class MonsterSceneLogic {
    private boolean isBroomUsed;
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
        golem = new Golem(50,200,1,player);
        addElement(golem); addMonster(golem);



    }


    public void addMagic() {
        Magic magic = new Magic();
        magic.setMagicState(getPlayer().getWalkState());
        addElement(magic);
        magicList.add(magic);
    }

    public boolean isBroomUsed() {
        return isBroomUsed;
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

//    public void checkMagicCollisionMonster(ArrayList<BaseMonster> monsters, Player player) {
//        ArrayList<Magic> magicList = getMagicList();
//        Iterator<BaseMonster> monsterIterator = monsters.iterator();
//
//        while (monsterIterator.hasNext()) {
//            BaseMonster monster = monsterIterator.next();
//            Iterator<Magic> magicIterator = magicList.iterator(); // Reset magic iterator for each monster
//
//            while (magicIterator.hasNext()) {
//                Magic magic = magicIterator.next();
//                magic.update();
//
//                if (magic.solidArea != null && magic.solidArea.getBoundsInParent().intersects(monster.solidArea.getBoundsInParent())) {
//                    double dx = monster.x - player.getX();
//                    double dy = monster.y - player.getY();
//                    magicIterator.remove();
//                    MonsterSceneLogic.getInstance().getMagicList().remove(magic);
//                    RenderableHolder.getInstance().remove((IRenderable) magic);
//
//                    // Normalize direction vector
//                    double distance = Math.sqrt(dx * dx + dy * dy);
//                    if (distance != 0) {
//                        dx /= distance;
//                        dy /= distance;
//                    }
//
//                    // Adjust monster's position in the opposite direction
//                    monster.x += 20 * dx;
//                    monster.y += 20 * dy;
//
//                    if (monster.getHP() - player.getDamage() <= 0) {
//                        monsterIterator.remove(); // Remove the current monster safely
//                        RenderableHolder.getInstance().remove((IRenderable) monster);
//                        System.out.println(monster.name + " died");
//                        dropItem(monster.x, monster.y);
//                    } else {
//                        monster.setHP(monster.getHP() - player.getDamage());
//                        System.out.println(monster.name + " was attacked by Player, HP: " + monster.getHP());
//                    }
//                }
//            }
//        }
//    }

    public void logicUpdate() {
        player.update();
        bat.update();
        golem.update();
        player.getAttacked(monsters);

        // Use iterator to safely remove magic elements while iterating
        Iterator<Magic> iterator = magicList.iterator();
        while (iterator.hasNext()) {
            Magic mg = iterator.next();
            mg.update();
            // Check if magic is out of bounds
            if (!(mg.getX() <= Config.sceneWidth - Config.playerWidth && mg.getX() >= 0) ||
                    !(mg.getY() <= Config.sceneHeight - Config.playerHeight && mg.getY() >= 0)) {
                // Remove magic from the list and renderable holder
                iterator.remove();
                RenderableHolder.getInstance().remove(mg);
            }
        }
        player.checkMagicCollisionMonster(monsters);

        // Check player collision with items after iterating over magicList
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
