package edu.csc413.tankgame.model;

public class Wall extends Entity{
    public Wall(String id, double x, double y) {
        super(id,x,y,0);
    }
    @Override
    public void move(GameWorld gameWorld)
    {
    }
}
