package gameclasses;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Class that contains an object of type map, which consists of a two-dimensional gridcell array.
 * Each gridcell has an x,y and cellSize.
 */
public class Map {

    private int cellSize;
    private int y;
    private int x;

    private JPanel panel;

    private GridCell[][] grid;
    
    private Player player;

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
                            this.player, cellSize, panel, GameSettings.getDefaultCellSprite());
            }
        }
    }

    /**
     * Draws the map, meaning a two dimensional array of gridcells.
     * @param g the graphics of the game.
     */
    public void drawGrid(Graphics g) {
        // Set grid color (optional)
        g.setColor(Color.green);

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
                
                if (cell.sprite != null) {
                    g.drawImage(cell.sprite, cell.getX(), cell.getY(), cellSize, cellSize, null);
                }
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

        //this.grid[column][row].displayOptions(this.grid);
        this.displayOptions(this.grid[column][row]);

    }

    public GridCell[][] getMap() {
        return this.grid;
    }

    public void displayOptions(GridCell gridCell) {
        
        // Possibly change this to JPanel for greater customization
        JPopupMenu optionsMenu = new JPopupMenu();

        ArrayList<GridCell> playerGridCells = new ArrayList<>();
        double playerXPosition = player.getXPosition();
        double playerYPosition = player.getYPosition();
        double playerSize = player.getWidth(); //Since player is a square

        int startCol = (int) (playerXPosition / cellSize);
        int endCol = (int) ((playerXPosition + playerSize) / cellSize);
        int startRow = (int) (playerYPosition / cellSize);
        int endRow = (int) ((playerYPosition + playerSize) / cellSize);

        startCol = Math.max(0, startCol);
        endCol = Math.min(x - 1, endCol);
        startRow = Math.max(0, startRow);
        endRow = Math.min(y - 1, endRow);

        for (int col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                GridCell cell = grid[col][row];
                playerGridCells.add(cell);
            }
        }

        //So that the player cannot build when the wave is started.
        if (!player.waveStarted) {
            if (!gridCell.occupied && !playerGridCells.contains(gridCell)) {
                if (gridCell.empty) {
                    // Add tower or wall
                    JMenuItem towerOption = new JMenuItem("Tower - " + GameSettings.TOWER_PRICE);
                    towerOption.addActionListener(e -> {
                        gridCell.buyTower(player, GameSettings.TOWER_PRICE, grid,
                                        GameSettings.towers);
                    });

                    optionsMenu.add(towerOption);

                    JMenuItem wallOption = new JMenuItem("Wall - " + GameSettings.WALL_PRICE);
                    wallOption.addActionListener(e -> {
                        gridCell.buyWall(player, GameSettings.WALL_PRICE, grid);
                    });

                    optionsMenu.add(wallOption);
                } else {
                    // Upgrade options
                }
            } else {
                JMenuItem destroyOption = new JMenuItem("Destroy - free");
                destroyOption.addActionListener(e -> {
                    gridCell.destroyObject(player, grid);
                });

                optionsMenu.add(destroyOption);
            }
        }

        // Calculate the position where the menu should appear (above the clicked cell)
        int popupX = gridCell.getX();
        int popupY = gridCell.getY() - optionsMenu.getPreferredSize().height;

        // Show the popup menu at the calculated position
        optionsMenu.show(this.panel, popupX, popupY);
    }
}
