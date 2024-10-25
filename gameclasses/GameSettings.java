package gameclasses;

public class GameSettings {
    public static final int INITIAL_GOLD = 100;
    public static final int TOWN_HALL_HEALTH = 500;
    public static final int PLAYER_DAMAGE = 5;
    public static final int TIME_BEFORE_WAVE_START = 10;  // seconds
    // FORMULA USED TO CALCULATE ENEMIES PER WAVE E(n) = E(n - 1) + 3
    public static final int ENEMY_DAMAGE = 10;
    public static final int TOWER_DAMAGE = 15;
    public static final int TOWER_PRICE = 50;
    public static final int WALL_PRICE = 10;
    public static final int BREAK_TIME_BETWEEN_WAVES = 8;  // seconds
    public static final int ENEMY_REWARD = 20;
    public static final int INITIAL_ENEMY_COUNT = 5;

    public static int getEnemyCount(int waveNumber) {
        return INITIAL_ENEMY_COUNT + (int)(Math.pow(waveNumber + 1, 2));
    }
}
