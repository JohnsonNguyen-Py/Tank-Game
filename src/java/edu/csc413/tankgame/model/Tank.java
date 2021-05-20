package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;

/** Entity class representing all tanks in the game. */
public abstract class Tank extends Entity {
    // TODO: Implement. A lot of what's below is relevant to all Entity types, not just Tanks. Move it accordingly to
    //       Entity class.

    private int shellCounter = 0; // makes each shell unique
    protected int shotCD = 0; //SHOOT COOLDOWN


    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);

    }

    // TODO: The methods below are provided so you don't have to do the math for movement. You should call these methods
    //       from the various subclasses of Entity in their implementations of move.


    //TAKEN FROM TANK CLASS.
    // SHELLs SHELsL SHELLs SHELLs PEW PEW PEW PEW PEW
    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    protected void fire(GameWorld gameWorld)
    {
        //System.out.println("testing"); // TESTING IT DOES WORK
        if(shotCD == 0)
        {
            Shell shell = new Shell(getId() + "shell-" + shellCounter, getShellX(), getShellY(), getAngle());
            gameWorld.addNewShells(shell);
            shellCounter++;
            shotCD = 200;
        }

    }
    protected void shellCooldown() {
        if (shotCD>0) {
            shotCD--;
        }
    }
    private double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

        //System.out.println("testing"); // TESTING IT DOES WORK
        /*Shell shell = new Shell(getId()+ "-shell-" + shellCounter, getX(), getY(), getAngle());
        gameWorld.addNewShells(shell);
        shellCounter++;*/
        //movements
        protected void moveForward(double movementSpeed) {
            setX(getX() + movementSpeed * Math.cos(getAngle()));
            setY(getY() + movementSpeed * Math.sin(getAngle()));
        }

    protected void moveBackward(double movementSpeed) {
        setX(getX() - movementSpeed * Math.cos(getAngle()));
        setY(getY() - movementSpeed * Math.sin(getAngle()));
    }

    protected void turnLeft(double turnSpeed) {
        setAngle(getAngle() - turnSpeed);
    }

    protected void turnRight(double turnSpeed) {
        setAngle(getAngle() + turnSpeed);
    }



}
