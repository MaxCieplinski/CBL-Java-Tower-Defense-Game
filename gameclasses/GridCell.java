package gameclasses;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


/**
 * Class that contains code for a single gridcell. 
 * The playing field is made from a lot of these gridcells.
 */
public class GridCell {
    
    Player player;
    int x;
    int y;
    JPanel panel;
    int price;
    int cellSize;

    ArrayList<Tower> towers;

    boolean occupied = false;
    boolean empty = true; // Example: Whether the cell is occupied by an object
    Color color = Color.GRAY;

    final int towerPrice = 300;
    final int wallPrice = 100;

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
        // TODO Destroy the JPopupMenu instance after clicking on other cell
        JPopupMenu optionsMenu = new JPopupMenu();

        if (!this.occupied) {
            if (this.empty) {
                // Add tower or wall
                JMenuItem towerOption = new JMenuItem("Tower - " + towerPrice);
                towerOption.addActionListener(e -> {
                    buyTower(player, towerPrice, grid);
                });
                optionsMenu.add(towerOption);

                JMenuItem wallOption = new JMenuItem("Wall - " + 100);
                wallOption.addActionListener(e -> {
                    buyWall(player, wallPrice, grid);
                });
                optionsMenu.add(wallOption);
            } else {
                // Upgrade options
            }
        } else {
            JMenuItem destroyOption = new JMenuItem("Destroy - free");
            optionsMenu.add(destroyOption);
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
        }
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
            grid[this.getX() / cellSize][this.getY() / cellSize].color = Color.green;
        }
    }

    public int getSize() {
        return this.cellSize;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void getTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }




}
