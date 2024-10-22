package gameclasses;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Collider object used for the Player class. This class handles conditions for collisions.
 */
public class Collider {

    private static final double OFFSET = 0.05;
    private Entity currEntity;

    /**
     * Creates a collider object.
     * @param e the entity on which this collide is 'placed'.
     */
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

                if (this.collidesWith(b) && !b.empty) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks for collision between colliders of enemies and bullets.
     * @param enemies arraylist of all enemies currently in the game.
     * @param player the player of the game.
     * @return true, if the collider of this bullet collides with an enemy.
     */
    public boolean checkForCollision(ArrayList<Enemy> enemies, Player player) {
        ArrayList<Enemy> enemies2 = new ArrayList<>(enemies);

        for (Enemy e : enemies2) {

            if ((this.collidesWith(e)) && (this != player.getCollider())) {
                ((Bullet) currEntity).handleHit(e);
                ((Bullet) currEntity).destroy();
                return true;
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
        return currEntity.getXPosition() - OFFSET < b.getX() + b.getSize()
            && currEntity.getXPosition() + currEntity.getWidth() + OFFSET > b.getX()
            && currEntity.getYPosition() - OFFSET < b.getY() + b.getSize()
            && currEntity.getYPosition() + currEntity.getHeight() + OFFSET > b.getY();

    }

    /**
     * Checks if an entity collides with an enemy (this code is used for bullets).
     * @param e the enemy for which the condition is checked.
     * @return true if the entity collides with the enemy, otherwise false.
     */
    private boolean collidesWith(Enemy e) {

        return currEntity.getXPosition() < e.getXPosition() + e.getWidth()
            && currEntity.getXPosition() + currEntity.getWidth() > e.getXPosition()
            && currEntity.getYPosition() < e.getYPosition() + e.getHeight()
            && currEntity.getYPosition() + currEntity.getHeight() > e.getYPosition();     
    }


}