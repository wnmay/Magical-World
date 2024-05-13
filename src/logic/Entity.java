package logic;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
    private int z;
    protected boolean visible;

    public Entity(){
        visible = true;
    }

    @Override
    public boolean isVisible(){
        return visible;
    }

    @Override
    public int getZ(){
        return z;
    }
}
