package gameclasses;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Menu panel class, which is on the top of the screen and holds labels to keep track of the game.
 */
public class MenuPanel extends JPanel {
    private int gold = GameSettings.INITIAL_GOLD;
    private String waveStatus;
    
    JButton townHallAddHealthButton = new JButton("Heal " + GameSettings.TOWN_HALL_HEAL_PERCENTAGE 
                                            + "% Health - " + GameSettings.TOWN_HALL_HEAL_PRICE);

    /**
     * Constructor for a menu panel object.
     */
    public MenuPanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1400, 200));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);

        setLayout(null);
        townHallAddHealthButton.setBounds(1150, 100, 200, 40);
        // Button click handled in constructor of gamepanel
        this.add(townHallAddHealthButton);
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

        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("Game Rules:", 500, 40);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("- You can only modify/place towers and walls before the wave starts",
                     500, 70);
        g.drawString("- You can only shoot during the wave", 500, 100);
        g.drawString("- Each killed enemy rewards you gold", 500, 130);
        g.drawString("- If the town hall gets destroyed you lose!", 500, 160);
        g.drawString("- You can use a bomb by pressing shift + click. A bomb costs " 
                    + GameSettings.PLAYER_BOMB_PRICE + " gold.",
                     500, 190);
    }

    /**
     * Getter method to set player gold on menu panel.
     * @param gold the amount of gold player has.
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Setter method to set current wave status on menu panel.
     * Used for example for wave start countdown.
     * @param waveStatus String of current wave status.
     */
    public void setWave(String waveStatus) {
        this.waveStatus = waveStatus;
    }

    /**
     * Setter method to set current town hall heal price on menu panel.
     * @param townHallHealPrice the current amount of gold for a town hall heal based on wave.
     */
    public void setTownHallHealPrice(int townHallHealPrice) {
        townHallAddHealthButton.setText("Heal 10% Health - " + Integer.toString(townHallHealPrice));
    }
}