package gameclasses;

import java.awt.Color;
import javax.swing.JLabel;

public class HealthBar {
    JLabel healthBar;
    int health;
    int maxHealth;
    GamePanel gamePanel;

    public HealthBar(GamePanel gamePanel, int health, int maxHealth) {
        this.gamePanel = gamePanel;
        this.health = health;
        this.maxHealth = maxHealth;

        //Create a healthBar for the enemy instance.
        this.healthBar = new JLabel();
        this.healthBar.setOpaque(true);
        this.healthBar.setBackground(Color.green);

        this.gamePanel.add(this.healthBar);
    }

    public void updateHealthBar() {

    }

    public void setHealthBarSize(int width, int height) {
        this.healthBar.setSize(width, height);
    }

    public void setHealthBarPosition(int x, int y) {
        this.healthBar.setLocation(x, y);
    }
}
