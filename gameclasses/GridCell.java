package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Class that contains code for a single gridcell. 
 * The playing field is made from a lot of these gridcells.
 */
public class GridCell {
    private Object cellObject;

    private Player player;
    
    private JPanel panel;

    private ArrayList<Tower> towers;

    private int x;
    private int y;
    private int cellSize;

    public boolean occupied = false;
    public boolean empty = true; // Example: Whether the cell is occupied by an object

    public int health;
    public int maxHealth;

    public Color color = Color.GRAY;
    
    public HealthBar healthBar;

    /**
     * Creates a grid cell object.
     * @param x x position.
     * @param y y position.
     */
    public GridCell(int x, int y, Player player, int cellSize, JPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.player = player;
        this.cellSize = cellSize;
    }

    /**
     * Displays buy options when a grid is pressed.
     * @param grid the playing field, a two-dimensional gridcell array.
     */
    public void displayOptions(GridCell[][] grid) {
        
        // Possibly change this to JPanel for greater customization
        JPopupMenu optionsMenu = new JPopupMenu();

        //So that the player cannot build when the wave is started.
        if (!player.waveStarted) {
            if (!this.occupied) {
                if (this.empty) {
                    // Add tower or wall
                    JMenuItem towerOption = new JMenuItem("Tower - " + GameSettings.TOWER_PRICE);
                    towerOption.addActionListener(e -> {
                        buyTower(player, GameSettings.TOWER_PRICE, grid);
                    });

                    optionsMenu.add(towerOption);

                    JMenuItem wallOption = new JMenuItem("Wall - " + GameSettings.WALL_PRICE);
                    wallOption.addActionListener(e -> {
                        buyWall(player, GameSettings.WALL_PRICE, grid);
                    });

                    optionsMenu.add(wallOption);
                } else {
                    // Upgrade options
                }
            } else {
                JMenuItem destroyOption = new JMenuItem("Destroy - free");
                destroyOption.addActionListener(e -> {
                    destroyObject(player, grid);
                });

                optionsMenu.add(destroyOption);
            }
        }

        // Calculate the position where the menu should appear (above the clicked cell)
        int popupX = this.x;
        int popupY = this.y - optionsMenu.getPreferredSize().height;

        // Show the popup menu at the calculated position
        optionsMenu.show(this.panel, popupX, popupY);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    /**
     * Code that handles buying a tower.
     * @param player the player of the game.
     * @param price the price of the tower.
     * @param grid the playing field, a two-dimensional array of gridcell.
     */
    public void buyTower(Player player, int price, GridCell[][] grid) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            Tower tower = new Tower(player, this.x, this.y, cellSize, this.panel);
            towers.add(tower);

            //Compensating for cellsize = 25;
            grid[this.getX() / cellSize][this.getY() / cellSize] = tower;
            grid[this.getX() / cellSize][this.getY() / cellSize].color = Color.MAGENTA;

            grid[this.getX() / cellSize][this.getY() / cellSize].cellObject = tower;
        }
    }

    /**
     * Destroys wall or tower.
     * @param player the player of the game.
     * @param grid the map, consisting of gridcells.
     */
    public void destroyObject(Player player, GridCell[][] grid) {
        Object object = grid[this.getX() / cellSize][this.getY() / cellSize].cellObject;

        try {
            if (object instanceof Tower) {
                towers.remove(object);
            }
        } catch (NullPointerException e) {
            return;
        }

        grid[this.getX() / cellSize][this.getY() / cellSize] = 
            new GridCell(this.getX(), this.getY(), player, this.cellSize, this.panel);
    }

    /**
     * Code that handles buying a wall.
     * @param player the player of the game.
     * @param price the price of the wall.
     * @param grid the playing field, a two-dimensional array of gridcell.
     */
    public void buyWall(Player player, int price, GridCell[][] grid) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            Wall wall = new Wall(this.x, this.y, player, panel);

            //Compensating for cellsize = 25;
            grid[this.getX() / cellSize][this.getY() / cellSize] = wall;
            grid[this.getX() / cellSize][this.getY() / cellSize].color = Color.orange;

            grid[this.getX() / cellSize][this.getY() / cellSize].cellObject = wall;
        }
    }

    public int getSize() {
        return this.cellSize;
    }

    public void getTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    /**
     * Checks for building destruction.
     * @return true when health is 0 or below 0 and false otherwise.
     */
    public boolean checkForDestruction(GridCell[][] grid) {
        if (this.health <= 0) {

            this.healthBar.healthBarBackground.setVisible(false);
            this.healthBar.healthBarForeground.setVisible(false);
            
            destroyObject(player, grid);

            return true;
        }

        return false;
    }
}