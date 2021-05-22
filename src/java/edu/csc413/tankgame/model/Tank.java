package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;

/** Entity class representing all tanks in the game. */
public abstract class Tank extends Entity {
    // TODO: Implement. A lot of what's below is relevant to all Entity types, not just Tanks. Move it accordingly to
    //       Entity class.

    private int shellCounter = 0; // makes each shell unique
    private int shotCD = 0; //SHOOT COOLDOWN
    private int tankHealth = 5;

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);

    }
    // TODO: The methods below are provided so you don't have to do the math for movement. You should call these methods
    //       from the various subclasses of Entity in their implementations of move.
    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    //HP stuff
    public void minusHP()
    {
     tankHealth -= 1;
      //System.out.println(getId() + "TESTING LOST HP");
    }

    public boolean tankDead()
    {
        if (tankHealth > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //SHELLS
    protected void shellCoolDown(GameWorld gameWorld)
    {
        //System.out.println("testing"); // TESTING IT DOES WORK
        if(shotCD == 0)
        {
            Shell shell = new Shell(getId() + "shell-" + shellCounter, getShellX(), getShellY(), getAngle());

            gameWorld.addNewShells(shell); //SHELL COUNTER. ANTHONY BORGES HELPED ME OUT. APPROVED BY DAWSON 5/18/2021

            shellCounter++;
            shotCD = 10;
        }

    }

    protected void fire(GameWorld gameWorld)
    {
        shellCoolDown(gameWorld);
        shotCD--;
    }

    private double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }



    //MOVEMENTS
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

    public void checkBoundsTank(GameWorld gameWorld) {
        // I checked bounds here for tank because I want to reuse it for the others as well.
        // If i were to do it inside player tank, i would've just copied and paste to the AI tanks.
        // the following instructions were on the hand out!

                if(getX() < Constants.TANK_X_LOWER_BOUND) {
                    //System.out.println("Testing tank bounds");
                    gameWorld.getEntity(getId()).setX(Constants.TANK_X_LOWER_BOUND);

                }
                if(getX() > Constants.TANK_X_UPPER_BOUND) {
                    gameWorld.getEntity(getId()).setX(Constants.TANK_X_UPPER_BOUND);
                }
                if(getY() < Constants.TANK_Y_LOWER_BOUND)
                {
                    gameWorld.getEntity(getId()).setY(Constants.TANK_Y_LOWER_BOUND);
                }
                if(getY()> Constants.TANK_Y_UPPER_BOUND)
                {
                    gameWorld.getEntity(getId()).setY(Constants.TANK_Y_UPPER_BOUND);
                }
    }




}
