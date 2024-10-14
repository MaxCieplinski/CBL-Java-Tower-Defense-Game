package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    final int fps = 60;

    ArrayList<Entity> entities = new ArrayList<>();

    Player player = new Player(0, 0, 4, this, entities, 25, 25);

    Map map = new Map(25, this, player);

    /**
     * Constructs a gamepanel object.
     */
    public GamePanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1400, 1000));
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

    public void update() {
        if (map.getMap() != null) {
            if (!player.getCollider().checkForCollision(map.getMap())) {
                player.updatePlayerPosition();
            } else {
                player.resetPlayerPosition();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.drawGrid(g);
        player.paintPlayer(g);
    }
}
