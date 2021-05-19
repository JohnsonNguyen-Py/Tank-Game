package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class Shell extends Tank{
    public Shell(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameWorld gameWorld)
    {
        moveForward(Constants.SHELL_MOVEMENT_SPEED);

    }

    /*public void checkBounds(GameWorld gameWorld)
    {

    }*/
}
