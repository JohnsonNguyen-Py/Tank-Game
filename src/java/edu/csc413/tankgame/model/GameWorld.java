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

    public GameWorld() {
        // TODO: Implement.
        entities = new ArrayList<>();
        shells = new ArrayList<>();

    }

    /** Returns a list of all entities in the game. */
    public List<Entity> getEntities() {
        // TODO: Implement.
        return entities;
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

    /** Returns the Entity with the specified ID. */
    public Entity getEntity(String id) {
        // TODO: Implement.
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
    }
    /*public void clearShells() // CLEAR SHELL BORGES
    { RAPID FIRE MODE
        if (shells.size() > 0)
        {
            for(int elem = 0; elem < shells.size(); elem++)
            {
                shells.remove(shells.get(elem));
            }
        }
    }*/

    /** Removes the entity with the specified ID from the game. */
    public void removeEntity(String id) {
        // TODO: Implement.

    }
}
