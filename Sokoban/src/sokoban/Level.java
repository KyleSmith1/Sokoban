package sokoban;

import java.io.File;

/**
 * @author 14001835
 */
public class Level {

    private MapElement map[];
    private int numberOfMoves;
    private WarehouseKeeper warehouseKeeper;
    private Crate crates[];
    
    public Level() {

    }

    public Level(int levelNumber) {
        File inputFile = new File("resources/SokobanMaps/level"+levelNumber+".txt");
        if (inputFile.exists()) {
            System.out.println("Found file textFile : " + inputFile.getName());
        } else {
            System.out.println("Cannot find file : " + inputFile.getAbsolutePath() + "/" + inputFile.getName());
        }
    }
    
    public int getNumberOfMoves(){
        return numberOfMoves;
    }
    
    public boolean checkLevelCompleted(){
        return false;
    }

}
