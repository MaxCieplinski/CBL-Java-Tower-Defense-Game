import gameclasses.GamePanel;
import javax.swing.*;

/**
 * Starts the tower defense game.
 */
public class Main {
    public static void main(String[] args) {
        //new Renderer();

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tower Defense");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
