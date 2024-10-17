package gameclasses;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Menu panel class, which is on the top of the screen and holds labels to keep track of the game.
 */
public class MenuPanel extends JPanel {

    int gold = 1000;
    String waveStatus;

    /**
     * Constructor for a menu panel object.
     */
    public MenuPanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1400, 200));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the first text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("Tower Defense", 40, 70);

        // Draw more text with a different font or color if needed
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("WAVE: " + waveStatus, 50, 140);
        g.drawString("GOLD: " + gold, 50, 170);
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setWave(String waveStatus) {
        this.waveStatus = waveStatus;
    }
}