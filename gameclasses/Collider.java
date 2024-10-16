package gameclasses;

import java.util.Arrays;

/**
 * Collider object used for the Player class. This class handles conditions for collisions.
 */
public class Collider {
    Entity currEntity;

    public Collider(Entity e) {
        this.currEntity = e;
    }

    /**
     * Checks for collision between player collider and buildings.
     * @param cells all gridcells, a.k.a. the playing field.
     * @return true if there is an occupied gridcell that collides with the player, otherwise false.
     */
    public boolean checkForCollision(GridCell[][] cells) {
        GridCell[][] cells2 = Arrays.copyOf(cells, cells.length);
        for (GridCell[] bArray : cells2) {
            for (GridCell b : bArray) {
                if (this.collidesWith(b) && !b.isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the player collides with a grid cell.
     * @param b the gridcell for which the condition is checked.
     * @return true if the player collides with the gridcell, otherwise false.
     */
    private boolean collidesWith(GridCell b) {
        //Offset so player doesn't enter inside the wall during animation.
        double offset = 0.05;
        return currEntity.getXPosition() - offset < b.getX() + b.getSize()
            && currEntity.getXPosition() + currEntity.getWidth() + offset > b.getX()
            && currEntity.getYPosition() - offset < b.getY() + b.getSize()
            && currEntity.getYPosition() + currEntity.getHeight() + offset > b.getY();

    }


}