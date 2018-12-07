package sokoban;

/**
 * @author 14001835
 */
public class SokobanGame {

    private Level CurrentLevel;
    
    public SokobanGame() {
        setLevel(1);
    }
    
    public void setLevel(int levelNumber){
        CurrentLevel = new Level(levelNumber);
    }
    
}
