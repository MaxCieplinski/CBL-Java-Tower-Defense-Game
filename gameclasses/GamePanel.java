package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.*;

/**
 * The panel that the game is run on.
 */
public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    final int fps = 60;

    MenuPanel menuPanel;

    ArrayList<Entity> entities = new ArrayList<>();

    TownHall townHall = new TownHall(5000, 700, 400, 75);

    Player player = new Player(0, 0, 4, this, entities, 20, 20);

    Map map = new Map(25, this, player);

    Wave wave = new Wave(player, this, townHall, map, 10, 1.2);

    /**
     * Constructs a gamepanel object.
     */
    public GamePanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1400, 800));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
    }

    /**
     * Starts the game.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            map.initializeGrid();
            wave.startWaveThread();
            //wave.run();
        });

        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer > 1000000000) {
                System.out.println("FPS: " + fps);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Update method, which is called every time after a set interval.
     */
    public void update() {
        if (map.getMap() != null) {
            if (!player.getCollider().checkForCollision(map.getMap())) {
                player.updatePlayerPosition();
            } else {
                player.resetPlayerPosition();
            }
        }
    }

    /**
     * Paints the gamepanel. So it draws the playing field and player.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.drawGrid(g);
        player.paintPlayer(g);
        
        if (wave.active) {
            wave.paintEnemies(g);
        }

        updateMenu(Optional.empty());
        townHall.paintTownHall(g);
    }

    /**
     * Updates menu panel.
     * @param menuPanel the panel that contains labels like gold, wave.
     */
    public void updateMenu(Optional<String> waveStatus) {
        SwingUtilities.invokeLater(() -> {
            menuPanel.setGold(player.getGold());
    
            // Only update wave status if present
            waveStatus.ifPresent(menuPanel::setWave);
    
            // Repaint the menu panel to reflect updates
            menuPanel.repaint();
        });
    }
}
