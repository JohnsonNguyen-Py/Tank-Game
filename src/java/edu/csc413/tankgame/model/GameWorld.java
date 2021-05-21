package edu.csc413.tankgame.model;

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


    /** Returns the Entity with the specified ID. */
    public Entity getEntity(String id) {
        // TODO: Implement.
        for (Entity findEntity: entities)
        {
            if (findEntity.getId() == id)
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
        //outOfBoundsShells.remove(getOutOfBoundsShells(id));



    }
}
