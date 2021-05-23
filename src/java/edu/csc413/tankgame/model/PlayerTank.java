package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;
import edu.csc413.tankgame.view.RunGameView;

public class PlayerTank extends Tank{

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

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
            //shellCooldown();
        }


    }

    @Override
    public void checkBounds(GameWorld gameWorld) {
        checkBoundsTank(gameWorld);
    }

    @Override
    public void handleCollision(GameWorld gameWorld) {
        handleCollision(gameWorld);
        gameWorld.addcollideTank(gameWorld.getEntity(getId()));
    }


    @Override
    public double getXBound(){
        return getX() + Constants.TANK_WIDTH;
    }
    @Override
    public double getYBound(){
        return getY() + Constants.TANK_HEIGHT;
    }
}
