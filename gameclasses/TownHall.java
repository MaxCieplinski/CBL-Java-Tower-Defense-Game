package gameclasses;

import java.awt.*;

public class TownHall {

    private int health;
    private int posX;
    private int posY;
    private int size;

    public TownHall(int health, int x, int y, int size) {

        this.health = health;
        this.posX = x;
        this.posY = y;
        this.size = size;

    }

    public void paintTownHall(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posX - size, posY - size, size, size);
    }
}
