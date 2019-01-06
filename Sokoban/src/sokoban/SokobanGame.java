package sokoban;

/**
 * @author 14001835
 */
public class SokobanGame {

    private Level CurrentLevel;

    public SokobanGame() {

    }

    public void setLevel(int levelNumber) {
        CurrentLevel = new Level(levelNumber);
    }

    public Level getMap(int LevelNumber) {
        return CurrentLevel;
    }

}
