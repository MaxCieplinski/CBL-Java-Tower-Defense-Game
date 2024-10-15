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
}
