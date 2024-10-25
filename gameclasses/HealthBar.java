package gameclasses;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * Healthbar class that contains all the code for health bars in the game.
 */
public class HealthBar {
    public JLabel healthBarBackground;
    public JLabel healthBarForeground;

    private int width;
    private int height;

    private GamePanel gamePanel;

    /**
     * Creates a healthbar.
     * @param gamePanel the panel the game is run on.
     * @param width the width of the healthbar.
     * @param height the height of the healthbar.
     */
    public HealthBar(GamePanel gamePanel, int width, int height) {
        this.gamePanel = gamePanel;
        this.width = width;
        this.height = height;

        //Create a healthBar for the enemy instance.
        this.healthBarBackground = new JLabel();
        this.healthBarBackground.setOpaque(true);
        this.healthBarBackground.setBackground(Color.green);
        
        this.healthBarForeground = new JLabel();
        this.healthBarForeground.setOpaque(true);
        this.healthBarForeground.setBackground(Color.red);

        setHealthBarSize(width, height);

        this.gamePanel.setLayout(null);

        this.gamePanel.add(this.healthBarBackground);
        this.gamePanel.add(this.healthBarForeground);
    }

    /**
     * Updates the healthbar.
     * @param health the health that needs to be displayed.
     */
    public void updateHealthBar(int health, int maxHealth) {
        double percentage = (double) health / maxHealth;
        int newWidth = (int) (this.width * percentage);

        this.healthBarBackground.setSize(newWidth, this.height);
    }

    /**
     * Sets the healthbar size.
     * @param width the width of the healthbar.
     * @param height the height of the healthbar.
     */
    public void setHealthBarSize(int width, int height) {
        this.healthBarBackground.setSize(width, height);
        this.healthBarForeground.setSize(width, height); 
    }

    /**
     * Sets the healthbar position.
     * @param x x-coordinate of the healthbar.
     * @param y y-coordinate of the healthbar.
     */
    public void setHealthBarPosition(int x, int y) {
        this.healthBarBackground.setLocation(x, y);
        this.healthBarForeground.setLocation(x, y);    
    }
}
