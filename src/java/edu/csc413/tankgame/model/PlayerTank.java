package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;

public class PlayerTank extends Tank{
    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }
    private int shellCounter = 0;

    @Override
    public void move(GameWorld gameWorld) {
        if (KeyboardReader.instance().upPressed()) {
            moveForward(Constants.TANK_MOVEMENT_SPEED);
        }
        if (KeyboardReader.instance().downPressed()) {
            moveBackward(Constants.TANK_MOVEMENT_SPEED);
        }
        if (KeyboardReader.instance().leftPressed()) {
            turnLeft(Constants.TANK_TURN_SPEED);
        }
        if (KeyboardReader.instance().rightPressed()) {
            turnRight(Constants.TANK_TURN_SPEED);
        }
        if(KeyboardReader.instance().spacePressed())
        {
            fire(gameWorld);
        }
    }
    private void fire(GameWorld gameWorld)
    {
        //System.out.println("testing"); // TESTING IT DOES WORK
        Shell shell = new Shell(getId()+ "-shell-" + shellCounter, getShellX(), getShellY(), getAngle());
        gameWorld.addNewShells(shell);
        shellCounter++;

        /*//System.out.println("testing"); // TESTING IT DOES WORK
        Shell shell = new Shell(getId()+ "-shell-" + shellCounter, getX(), getY(), getAngle());
        gameWorld.addNewShells(shell);
        shellCounter++;*/

    }
    //TAKEN FROM TANK CLASS.
    private double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

}
