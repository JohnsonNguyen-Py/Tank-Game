package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

import java.util.*;

/**
 * GameWorld holds all of the model objects present in the game. GameWorld tracks all moving entities like tanks and
 * shells, and provides access to this information for any code that needs it (such as GameDriver or entity classes).
 */
public class GameWorld {
    // TODO: Implement. There's a lot of information the GameState will need to store to provide contextual information.
    //       Add whatever instance variables, constructors, and methods are needed.
    private final List<Entity> entities;
    private final List<Entity> shells; //THIS IS FOR THE FIRED SHELLS
    private final List<Entity> outOfBoundsShells;


    public GameWorld() {
        // TODO: Implement.
        entities = new ArrayList<>();
        shells = new ArrayList<>();
        outOfBoundsShells = new ArrayList<>();
    }

    /** Returns a list of all entities in the game. */
    public List<Entity> getEntities() {
        // TODO: Implement.
        return entities;
    }


    public void addOOBShells(Entity entity) // ADDS OUT OF BOUND SHELLS
    {
        outOfBoundsShells.add(entity);
    }
    public void addNewShells(Entity entity) //I believe this is the correct way.
    {
        shells.add(entity);
    }
    /** Adds a new entity to the game. */
    public void addEntity(Entity entity) {
        // TODO: Implement.
        entities.add(entity);

    }

    public List<Entity> getOutOfBoundsShells() { // I wanted to use entity entity but then itellij yelled at me
        return outOfBoundsShells;
    }

    public void clearOOBShells()
    {
        outOfBoundsShells.clear();
    }


    /** Returns the Entity with the specified ID.
     * @param id*/
    public Entity getEntity(String id) {
        // TODO: Implement.
        for (Entity findEntity: entities)
        {
            if (findEntity.getId() ==id)
            {
                return findEntity;
            }
        }
        return null;
    }

    public List<Entity> getShells()
    {
        return shells;
    }

    public void combineList()
    {
        entities.addAll(getShells());
    }

    public void clearShells()
    {
        shells.removeAll(shells);
    } //ANTHONY BORGES HELPED ME OUT. APPROVED BY DAWSON 5/19/2021 OFFICE HOURS


    /** Removes the entity with the specified ID from the game. */
    public void removeEntity(String id) {
        // TODO: Implement.
        entities.remove(getEntity(id));
        //outOfBoundsShells.remove(getOutOfBoundsShells());
    }
    /*● First entity’s x coordinate < second entity’s x bound AND
   ●  First entity’s x bound > second entity’s x coordinate AND
   ● First entity’s y coordinate < second entity’s y bound AND
   ● First entity’s y bound > second entity’s y coordinate.*/ //THIS IS FROM HAND OUT
    public boolean entitiesOverlap(Entity entity1, Entity entity2) { //LECTURE
        return entity1.getX() < entity2.getXBound()
                && entity1.getXBound() > entity2.getX()
                && entity1.getY() < entity2.getYBound()
                && entity1.getYBound() > entity2.getY();
    }
     /*Calculate each of these four distances. The shortest of these four distances determines how we
    then adjust the two tanks:
    1. If tankA.getXBound() - tankB.getX() is the smallest distance, then we move Tank A
    to the left by half that distance and Tank B to the right by half that distance.
    2. If tankB.getXBound() - tankA.getX() is the smallest distance, then we move Tank A
    to the right by half that distance and Tank B to the left by half that distance.
    3. If tankA.getYBound() - tankB.getY() is the smallest distance, then we move Tank A
    upward by half that distance and Tank B downward by half that distance.
    4. If tankB.getYBound() - tankA.getY() is the smallest distance, then we move Tank A
    downward by half that distance and Tank B upward by half that distance.
    */


    private void handleCollision(Entity entity1, Entity entity2) {

        double collNum1x = entity1.getXBound()-entity2.getX();
        double collNum2x = entity2.getXBound()-entity1.getX();
        double collNum3y = entity1.getYBound()-entity2.getY();
        double collNum4y = entity2.getYBound()-entity1.getY();



        if (entity1 instanceof Tank && entity2 instanceof Tank) {
            if(entitiesOverlap(entity1,entity2)){
                entity1.setX(entity1.getX()-(collNum1x/2));
                entity2.setX(entity2.getX()+(collNum2x/2));}
            else if (entitiesOverlap(entity1,entity2))
            {
                entity1.setX(entity1.getX()+(collNum1x/2));
                entity2.setX(entity2.getX()-(collNum2x/2));
            }
            else if (entitiesOverlap(entity1,entity2))
            {
                entity1.setX(entity1.getY()+(collNum3y/2));
                entity2.setX(entity2.getY()-(collNum4y/2));
            }
            else if (entitiesOverlap(entity1,entity2))
            {
                entity1.setX(entity1.getY()-(collNum3y/2));
                entity2.setX(entity2.getY()+(collNum4y/2));
            }


        } else if (entity1 instanceof Tank && entity2 instanceof Shell) {


        } else if (entity1 instanceof Shell && entity2 instanceof Tank) {

        }else if (entity1 instanceof Wall && entity2 instanceof Shell) {

        }
    }


}
