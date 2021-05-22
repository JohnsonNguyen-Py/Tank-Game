package edu.csc413.tankgame;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameDriver {
    private final MainView mainView;
    private final RunGameView runGameView;
    private final List<Entity> entities;
    private final GameWorld gameWorld; //NEED THIS TO RUN
    private int wallNum =0;

    public GameDriver() {
        mainView = new MainView(this::startMenuActionPerformed);
        runGameView = mainView.getRunGameView();
        entities = new ArrayList<>();
        gameWorld = new GameWorld();
    }

    public void start() {
        mainView.setScreen(MainView.Screen.START_GAME_SCREEN);
    }

    private void startMenuActionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case StartMenuView.START_BUTTON_ACTION_COMMAND -> runGame();
            case StartMenuView.EXIT_BUTTON_ACTION_COMMAND -> mainView.closeGame();
            default -> throw new RuntimeException("Unexpected action command: " + actionEvent.getActionCommand());
        }
    }

    private void runGame() {
        mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
        Runnable gameRunner = () -> {
            setUpGame();
            while (updateGame()) {
                runGameView.repaint();
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
            resetGame();
        };
        new Thread(gameRunner).start();
    }

    /**
     * setUpGame is called once at the beginning when the game is started. Entities that are present from the start
     * should be initialized here, with their corresponding sprites added to the RunGameView.
     */
    private void setUpGame(){
        // TODO: Implement.

        for (WallInformation wall: WallInformation.readWalls())
        {
            Wall addWall = new Wall(
                    "Wall" + wallNum,wall.getX(),wall.getY()
            );
            wallNum++;
            gameWorld.addEntity(addWall);
            runGameView.addSprite(
                    addWall.getId(),
                    wall.getImageFile(),
                    addWall.getX(),
                    addWall.getY(),0
            );
        }


        PlayerTank playerTank = new PlayerTank(
                Constants.PLAYER_TANK_ID,
                Constants.PLAYER_TANK_INITIAL_X,
                Constants.PLAYER_TANK_INITIAL_Y,
                Constants.PLAYER_TANK_INITIAL_ANGLE);
        entities.add(playerTank);

        runGameView.addSprite(
                    playerTank.getId(),
                    RunGameView.PLAYER_TANK_IMAGE_FILE,
                    playerTank.getX(),
                    playerTank.getY(),
                    playerTank.getAngle() //FROM LECTURE THANK YOU THE GOAT DAWSON
        );
        gameWorld.addEntity(playerTank);

        DumbAiTank aiTank = new DumbAiTank(
                Constants.AI_TANK_1_ID,
                Constants.AI_TANK_1_INITIAL_X,
                Constants.AI_TANK_1_INITIAL_Y,
                Constants.AI_TANK_1_INITIAL_ANGLE);
        entities.add(aiTank);
        runGameView.addSprite(
                aiTank.getId(),
                RunGameView.AI_TANK_IMAGE_FILE,
                aiTank.getX(),
                aiTank.getY(),
                aiTank.getAngle()
        );
        gameWorld.addEntity(aiTank);

        aiTankTwo aiTankTwo = new aiTankTwo(
                Constants.AI_TANK_2_ID,
                Constants.AI_TANK_2_INITIAL_X,
                Constants.AI_TANK_2_INITIAL_Y,
                Constants.AI_TANK_2_INITIAL_ANGLE);
        entities.add(aiTankTwo);
        runGameView.addSprite(
                aiTankTwo.getId(),
                RunGameView.AI_TANK_IMAGE_FILE,
                aiTankTwo.getX(),
                aiTankTwo.getY(),
                aiTankTwo.getAngle()
        );
        gameWorld.addEntity(aiTankTwo); //.ADD ENTITY CLEAN UP SOON

    }

    /**
     * updateGame is repeatedly called in the gameplay loop. The code in this method should run a single frame of the
     * game. As long as it returns true, the game will continue running. If the game should stop for whatever reason
     * (e.g. the player tank being destroyed, escape being pressed), it should return false.
     */
    private boolean updateGame() {
        // TODO: Implement.


        ArrayList<Entity> originalEntities = new ArrayList<>(gameWorld.getEntities());
        for (Entity entity: originalEntities)
        {
            entity.move(gameWorld);
        }

        for (Entity entity: gameWorld.getShells()) // add shells
        {
           runGameView.addSprite(
                    entity.getId(),
                   RunGameView.SHELL_IMAGE_FILE,
                   entity.getX(),
                   entity.getY(),
                   entity.getAngle()
           );
            gameWorld.combineList();
        }

        if (gameWorld.getShells().size() > 0) // clear the shells
        {
            gameWorld.clearShells(); 
        }

        for (Entity entity: gameWorld.getOutOfBoundsShells()) // deleting out of bound shells
        {
            //System.out.println( "testing");
                gameWorld.removeEntity(entity.getId()); // DELETES SHELL ID
           // System.out.println("tseting");
                runGameView.removeSprite(entity.getId()); // DELETES SPRITE
                runGameView.addAnimation(
                RunGameView.BIG_EXPLOSION_ANIMATION,
                RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                entity.getX(),
                entity.getY()); // HOLY CRAP IT WORKS NOW AKJSDKAHLSDA WOOWOWOWOWO LETS GOOOOO
                                // also thank you dawson for this idea in the hand out. :)
        }

        if(gameWorld.getOutOfBoundsShells().size() > 0)
        {
            gameWorld.clearOOBShells();
        }
        for (Entity entity: gameWorld.getEntities()) // I tried to do it this way for Shell's but then it did not work
        {

            entity.checkBounds(gameWorld); // THIS CALLS THE CHECKBOUNDS FOR THE TANK. CANNOT PUT SHELL STUFF HERE.
        }

        for (Entity entity: gameWorld.getEntities())
        {
            if(gameWorld.entitiesOverlap(entity,entity)){
                System.out.println(entity.getId() + " is colliding with " + entity.getId());



            }
        }

        for (Entity entity: gameWorld.getEntities())
        {
            runGameView.setSpriteLocationAndAngle(entity.getId(), entity.getX(), entity.getY(), entity.getAngle());
        }

        return true;

    }






    /**
     * resetGame is called at the end of the game once the gameplay loop exits. This should clear any existing data from
     * the game so that if the game is restarted, there aren't any things leftover from the previous run.
     */
    private void resetGame() {
        // TODO: Implement.
        runGameView.reset();
    }

    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }
}
