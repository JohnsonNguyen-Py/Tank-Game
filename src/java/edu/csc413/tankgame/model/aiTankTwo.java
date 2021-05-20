package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class aiTankTwo extends Tank{
        public aiTankTwo(String id, double x, double y, double angle) {
            super(id,x,y,angle);
        }


        @Override
        public void move(GameWorld gameWorld) {
            Entity playerTank = gameWorld.getEntity(Constants.PLAYER_TANK_ID);
            fire(gameWorld);

        }
}
