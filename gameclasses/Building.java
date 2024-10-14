package gameclasses;

import gameclasses.Map;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Building {
    enum CELL_VALUE {
        EMPTY
    }
    
    Player player;
    int x;
    int y;
    JPanel panel;
    int price;
    int cellSize;

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
    public Building(int x, int y, Player player, int cellSize, JPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.player = player;
        this.cellSize = cellSize;
    }

    public void displayOptions(Building[][] grid) {
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

    public void buy(Player player) {
        player.subtractGold(price);
    }


    public void buyTower(Player player, int price, Building[][] grid) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            Tower tower = new Tower(player, this.x, this.y, 25, this.panel);
            //System.out.println(this.x);
            grid[this.getX() / 25][this.getY() / 25] = tower;
            grid[this.getX() / 25][this.getY() / 25].color = Color.MAGENTA;
            tower.empty = false;
            tower.occupied = true;
        }
    }

    public void buyWall(Player player, int price, Building[][] grid) {
        if (player.getGold() >= price) {
            player.subtractGold(price);
            Wall wall = new Wall(this.x, this.y, player, panel);
            grid[this.getX() / 25][this.getY() / 25] = wall;
            grid[this.getX() / 25][this.getY() / 25].color = Color.green;
            wall.empty = false;
            wall.occupied = true;
        }
    }

    public int getSize() {
        return this.cellSize;
    }

    public boolean isEmpty() {
        return empty;
    }


}
