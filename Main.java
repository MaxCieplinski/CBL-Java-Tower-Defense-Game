import gameclasses.GamePanel;
import gameclasses.MenuPanel;
import java.awt.BorderLayout;
import java.lang.classfile.instruction.ThrowInstruction;
import javax.swing.*;

/**
 * Starts the tower defense game.
 */
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tower Defense");

        window.setLayout(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();
        window.add(menuPanel, BorderLayout.NORTH);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel, BorderLayout.CENTER);

        window.setSize(1400, 1000);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.requestFocusInWindow();

        gamePanel.startGameThread();
    }
}
