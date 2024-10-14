package gameclasses;

import java.util.ArrayList;

/**
 * Class that contains the code for an entity object. 
 * This class has a position, width and height, and collider.
 * It stores the old x and y-values, so that the position can be reset when colliding.
 */

public class Entity {
    double x;
    double y;

    Collider collider;

    int size;

    double oldX;
    double oldY;

    double width;
    double height;

    /**
     * Creates an entity.
     * @param posX x-position.
     * @param posY y-position.
     * @param entities array of all entities before the current one.
     * @param width width of entity.
     * @param height height of entity.
     */

    public Entity(double posX, double posY, ArrayList<Entity> entities,
                 double width, double height) {
        this.x = posX;
        this.y = posY;

        oldX = posX;
        oldY = posY;

        this.width = width;
        this.height = height;

        //Makes new collider for the new entity.
        this.collider = new Collider(this);

        entities.add(this);
    }

    /**
     * Method which sets the entity position.
     * @param xPosition the x coordinate of new Entity position.
     * @param yPosition the y coordinate of new Entity position.
     */

    public void setPosition(double xPosition, double yPosition) {
        oldX = this.x;
        oldY = this.y;

        this.x = xPosition;
        this.y = yPosition;
    }

    /**
     * Method to get the x coordinate.
     * @return The x coordinate of Entity position.
     */

    public double getXPosition() {
        return x;
    }

    /**
     * Method to get the y coordinate.
     * @return The y coordinate of Entity position.
     */

    public double getYPosition() {
        return y;
    }

    public Collider getCollider() {
        return this.collider;
    }

    public double getSize() {
        return size;
    }


    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

}