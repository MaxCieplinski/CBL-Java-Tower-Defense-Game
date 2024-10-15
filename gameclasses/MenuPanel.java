package gameclasses;
import java.awt.*;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1400, 200));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the first text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("Tower Defense", 40, 70);

        // Draw more text with a different font or color if needed
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("WAVE: ", 50, 140);
        g.drawString("GOLD: ", 50, 170);  
    }
}