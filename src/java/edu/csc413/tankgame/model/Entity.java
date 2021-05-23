package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

/**
 * A general concept for an entity in the Tank Game. This includes everything that can move or be interacted with, such
 * as tanks, shells, walls, power ups, etc.
 */
public abstract class Entity {
    private String id;
    private double x;
    private double y;
    private double angle;

    public Entity(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {this.x =x;}
    public void setY(double y) {this.y =y;}
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }



    public abstract double getXBound(); // COLLISION Advised by Dawson in handout
    public abstract double getYBound(); // COLLISION Advised by Dawson in handout
    /** All entities can move, even if the details of their move logic may vary based on the specific type of Entity. */
    public abstract void move(GameWorld gameWorld);
    public abstract void checkBounds(GameWorld gameWorld);
    public abstract void handleCollision(GameWorld gameWorld);

    //public abstract void removeEntity(GameWorld gameWorld, RunGameView runGameView);
}
