package gameclasses;

import javax.swing.*;

/**
 * Wall class, a special type of gridcell, which contains a wall.
 */
public class Wall extends GridCell {
    
    /**
     * Construct a wall.
     * @param x x-coordinate of the tile where the wall is.
     * @param y y-coordinate of the tile where the wall is.
     * @param player the player of the game.
     * @param panel the panel on which the wall will be drawn.
     */
    public Wall(int x, int y, Player player, JPanel panel) {
        super(x, y, player, 25, panel, GameSettings.getWallSprite());
        super.health = GameSettings.WALL_HEALTH;
        super.maxHealth = GameSettings.WALL_HEALTH;

        super.occupied = true;
        super.empty = false;
        super.healthBar = new HealthBar(panel, 25, 2);

        //Compensate for cellSize = 25.
        this.healthBar.setHealthBarPosition(x, y - 5);
    }
}
