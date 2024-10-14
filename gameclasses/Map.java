package gameclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Map {
    int cellSize;
    int y;
    int x;
    JPanel panel;
    Building[][] grid;
    Player player;

    public Map(int cellSize, JPanel panel, Player player) {
        this.cellSize = cellSize;
        this.panel = panel;
        this.player = player;

        //Checks for mouse press.
        this.panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cellPressed(e);
            }
        });
    }

    public void initializeGrid() {
        System.out.println("Initializing grid...");
        y = this.panel.getHeight() / cellSize;
        x = this.panel.getWidth() / cellSize;
        grid = new Building[x][y];

        for (int col = 0; col < x; col++) {
            for (int row = 0; row < y; row++) {
                // Create a new GridCell at each position (row, col) based on cell size
                grid[col][row] = new Building(col * cellSize, row * cellSize, 
                            this.player, cellSize, panel);
            }
        }
    }

    public void drawGrid(Graphics g) {
        // Set grid color (optional)
        g.setColor(Color.LIGHT_GRAY);

        // Iterate through each cell in the grid and draw it
        for (int col = 0; col < x; col++) {
            for (int row = 0; row < y; row++) {
                Building cell = grid[col][row];
                g.setColor(cell.color);
                g.fillRect(cell.getX(), cell.getY(),
                            cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(cell.getX(), cell.getY(),
                            cellSize, cellSize);
            }
        }
    }

    /**
     * Changes color of current cell pressed.
     * @param e event when cell is pressed.
     */
    private void cellPressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Calculate the column and row
        int column = mouseX / this.cellSize; // Calculate column based on x coordinate
        int row = mouseY / this.cellSize;    // Calculate row based on y coordinate

        this.grid[column][row].displayOptions(this.grid);
    }


    public Building[][] getMap() {
        return this.grid;
    }
}
