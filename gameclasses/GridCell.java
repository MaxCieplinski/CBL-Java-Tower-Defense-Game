package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class that contains code for a single gridcell. 
 * The playing field is made from a lot of these gridcells.
 */
public class GridCell {
    private Player player;
    
    private JPanel panel;

    private int x;
    private int y;
    private int cellSize;

    public boolean occupied = false;
    public boolean empty = true; // Example: Whether the cell is occupied by an object
    public boolean towerRangeOn;

    public int health;
    public int maxHealth;

    public Color color = Color.decode("#408c3b");
    
    public HealthBar healthBar;
    public Image sprite;

    /**
     * Constructor method for GridCell class.
     * Creates a grid cell object.
     * @param x x position.
     * @param y y position.
     * @param player the player object - main player of the game.
     * @param cellSize the size of a cell.
     * @param panel the panel the game is rendered on.
     * @param sprite Sprite of Image type of whatever is on current gridcell, 
     *               null for default sprite.
     */
    public GridCell(int x, int y, Player player, int cellSize, JPanel panel, Image sprite) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.player = player;
        this.cellSize = cellSize;
        this.sprite = sprite;
    }

    /**
     * Getter method to get grid cell x position.
     * @return x position of grid cell.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter method to get grid cell y position.
     * @return y position of grid cell.
     */
    public int getY() {
        return this.y;
    }


    /**
     * Code that handles buying a tower.
     * @param player The player of the game.
     * @param price The price of the tower.
     * @param grid 2d array of GridCell type, the map/grid.
     */
    public void buyTower(Player player, int price, GridCell[][] grid) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            Tower tower = new Tower(player, this.x, this.y, cellSize, this.panel);
            GameSettings.towers.add(tower);

            //Compensating for cellsize = 25;
            grid[this.getX() / cellSize][this.getY() / cellSize] = tower;
        }
    }

    /**
     * Destroys wall or tower.
     * @param player The player of the game.
     * @param grid 2d array of GridCell type, the map/grid.
     */
    public void destroyObject(Player player, GridCell[][] grid) {
        try {
            if (this instanceof Tower) {
                //Remove tower showing range circle.
                this.towerRangeOn = false;
                GameSettings.towers.remove(this);
            }
        } catch (NullPointerException e) {
            return;
        }

        this.healthBar.healthBarBackground.setVisible(false);
        this.healthBar.healthBarForeground.setVisible(false);
        
        grid[this.getX() / cellSize][this.getY() / cellSize] = 
            new GridCell(this.getX(), this.getY(), player, this.cellSize, this.panel, null);
    }

    /**
     * Code that handles buying a wall.
     * @param player The player of the game.
     * @param price The price of the wall.
     * @param grid 2d array of GridCell type, the map/grid.
     */
    public void buyWall(Player player, int price, GridCell[][] grid) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            Wall wall = new Wall(this.x, this.y, player, panel);

            //Compensating for cellsize = 25;
            grid[this.getX() / cellSize][this.getY() / cellSize] = wall;
            grid[this.getX() / cellSize][this.getY() / cellSize].color = Color.orange;
        }
    }

    /**
     * Getter method to get cell size.
     * @return the cell size.
     */
    public int getSize() {
        return this.cellSize;
    }
    
    /**
     * Checks for building destruction.
     * @param grid 2d array of GridCell type, the map/grid.
     * @return true when health is 0 or below 0 and false otherwise.
     */
    public boolean checkForDestruction(GridCell[][] grid) {
        if (this.health <= 0) {
            destroyObject(player, grid);

            return true;
        }

        return false;
    }
}