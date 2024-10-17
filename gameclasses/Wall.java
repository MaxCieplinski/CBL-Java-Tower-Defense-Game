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
        super(x, y, player, 25, panel);
        super.occupied = true;
        super.empty = false;
    }
}
