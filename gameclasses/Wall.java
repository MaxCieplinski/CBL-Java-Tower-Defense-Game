package gameclasses;

import java.util.ArrayList;
import javax.swing.*;

public class Wall extends GridCell{


    public Wall(int x, int y, Player player, JPanel panel) {
        super(x, y, player, 25, panel);
        super.occupied = true;
        super.empty = false;
    }
}
