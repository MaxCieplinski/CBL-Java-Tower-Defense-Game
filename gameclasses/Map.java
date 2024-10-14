package gameclasses;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


/**
 * Class that contains an object of type map, which consists of a two-dimensional gridcell array.
 * Each gridcell has an x,y and cellSize.
 */
public class Map {
    int cellSize;
    int y;
    int x;
    JPanel panel;
    GridCell[][] grid;
    Player player;

    /**
     * Constructs an object of type map.
     * @param cellSize size of each grid.
     * @param panel the JPanel object.
     * @param player the object of type player.
     */
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

    /**
     * Initializes the gridcells.
     */
    public void initializeGrid() {
        System.out.println("Initializing grid...");
        y = this.panel.getHeight() / cellSize;
        x = this.panel.getWidth() / cellSize;
        grid = new GridCell[x][y];

        for (int col = 0; col < x; col++) {
            for (int row = 0; row < y; row++) {
                // Create a new GridCell at each position (row, col) based on cell size
                grid[col][row] = new GridCell(col * cellSize, row * cellSize, 
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
                GridCell cell = grid[col][row];
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


    public GridCell[][] getMap() {
        return this.grid;
    }
}
