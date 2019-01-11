package sokoban;

/**
 * @author 14001835
 */
public class SokobanGame {

    private Level CurrentLevel;

    public SokobanGame() {

    }

    public void setLevel(int levelNumber) {
        //Sets the current level to the level passed into the parameter
        CurrentLevel = new Level(levelNumber);
    }

    public Level getMap(int LevelNumber) {
        //Gets the current level
        return CurrentLevel;
    }

}
