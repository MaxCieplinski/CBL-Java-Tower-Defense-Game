package gameclasses;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Collider object used for the Player class. This class handles conditions for collisions.
 */
public class Collider {

    private static final double OFFSET = 0.05;
    private Entity currEntity;
    private ArrayList<GridCell> collisionCells;

    /**
     * Creates a collider object.
     * @param e the entity on which this collide is 'placed'.
     */
    public Collider(Entity e) {
        this.currEntity = e;
    }

    /**
     * Checks for collision between current entity collider and buildings.
     * @param cells all gridcells, a.k.a. the playing field.
     * @return true if there is an occupied gridcell that collides with the entity, otherwise false.
     */
    public boolean checkForCollision(GridCell[][] cells) {
        GridCell[][] cells2 = Arrays.copyOf(cells, cells.length);
        collisionCells = new ArrayList<>();

        for (GridCell[] bArray : cells2) {
            for (GridCell b : bArray) {

                if (this.collidesWith(b) && !b.empty) {
                    collisionCells.add(b);
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
     * Checks if the current entity collides with a grid cell.
     * @param b the gridcell for which the condition is checked.
     * @return true if the current entity collides with the gridcell, otherwise false.
     */
    private boolean collidesWith(GridCell b) {
        //Offset so the entity doesn't enter inside the wall during animation.
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

    /**
     * Makes walls or tower takes damage upon enemy collision.
     */
    public void destroyBuildings(GridCell[][] grid, Enemy e) {

        for (GridCell b : collisionCells) {
            if (b.occupied) {
                b.health -= e.buildingDamage;
                b.checkForDestruction(grid);
                System.out.println(b.health);
                b.healthBar.updateHealthBar(b.health, b.maxHealth);
            }
        }

        collisionCells.clear();
    }


}