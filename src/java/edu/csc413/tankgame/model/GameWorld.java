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



}
