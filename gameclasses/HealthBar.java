package gameclasses;

import java.awt.Color;
import javax.swing.JLabel;

public class HealthBar {
    JLabel healthBarBackground;
    JLabel healthBarForeground;
    int health;
    int maxHealth;
    int width;
    int height;
    GamePanel gamePanel;

    public HealthBar(GamePanel gamePanel, int health, int maxHealth, int width, int height) {
        this.gamePanel = gamePanel;
        this.health = health;
        this.maxHealth = maxHealth;
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

    public void updateHealthBar(int health) {
        double percentage = (double) health / this.maxHealth;
        int newWidth = (int) (this.width * percentage);

        this.healthBarBackground.setSize(newWidth, this.height);
    }

    public void setHealthBarSize(int width, int height) {
        this.healthBarBackground.setSize(width, height);
        this.healthBarForeground.setSize(width, height); 
    }

    public void setHealthBarPosition(int x, int y) {
        this.healthBarBackground.setLocation(x, y);
        this.healthBarForeground.setLocation(x, y);    
    }
}
