package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class Wall extends Entity{

    public Wall(String id, double x, double y) {
        super(id,x,y,0);

    }



    @Override
    public void move(GameWorld gameWorld)
    {

    }

    @Override
    public void checkBounds(GameWorld gameWorld) {

    }

    @Override
    public void handleCollision(GameWorld gameWorld) {
        handleCollision(gameWorld);

        gameWorld.addcollideShells(gameWorld.getEntity(getId()));
    }


    @Override
    public double getXBound(){
        return getX() + Constants.WALL_WIDTH;
    }
    @Override
    public double getYBound(){
        return getY() + Constants.WALL_HEIGHT;
    }
}
