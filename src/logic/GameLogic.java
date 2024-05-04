package logic;


import sharedObject.RenderableHolder;

public class GameLogic {


    public GameLogic(){
        Map map=new Map();
        RenderableHolder.getInstance().add(map);
    }
}
