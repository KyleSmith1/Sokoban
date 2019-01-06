package sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author 14001835
 */
public class Level {

    private MapElement map[][] = new MapElement[24][15];
    private int numberOfMoves;
    private WarehouseKeeper warehouseKeeper;
    private Crate crates[] = new Crate[10];

    public Level() {

    }

    public Level(int levelNumber) {

        File inputFile = new File("resources/SokobanMaps/level" + levelNumber + ".txt");

        FileReader reader = null;
        BufferedReader inputBuffer = null;

        int lineLength = 0;

        try {

            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);
            String inputLine = inputBuffer.readLine();
            lineLength = inputLine.length();

            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);
            int input = inputBuffer.read();

            while (input != -1) {

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {

                        char c = (char) input;
                        
                        //Tile
                        if (c == ' ') {
                            Tile newFloor = new Tile();
                            if (map[i][j] == null) {
                                map[i][j] = newFloor;
                                break;
                            }

                        } //Wall
                        else if (c == 'X') {
                            Wall newWall = new Wall();
                            if (map[i][j] == null) {
                                map[i][j] = newWall;
                                break;
                            }

                        } //Crate
                        else if (c == '*') {
                            Crate newCrate = new Crate();
                            for (int k = 0; k < crates.length; k++) {
                                if (crates[k] == null) {
                                    crates[k] = newCrate;
                                    break;
                                }
                            }

                            if (map[i][j] == null) {
                                map[i][j] = newCrate;
                                break;
                            }

                        } //Diamond
                        else if (c == '.') {
                            Diamond newDiamond = new Diamond();
                            if (map[i][j] == null) {
                                map[i][j] = newDiamond;
                                break;
                            }

                        } //Warehouse Keeper
                        else if (c == '@') {
                            WarehouseKeeper newKeeper = new WarehouseKeeper();
                            if (map[i][j] == null) {
                                map[i][j] = newKeeper;
                                break;
                            }
                        }
                        /*
                        if (map[i][j] != null) {
                            String tileType = map[i][j].displayImage();
                            Image image = new Image("file:resources/SokobanImages/" + tileType);
                            ImageView im = new ImageView();
                            im.setImage(image);

                            gridPane.add(im, i, j);

                        }
                        */

                    }
                    input = inputBuffer.read();
                }

            }

            map[0][0].displayImage();
            
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (inputBuffer != null) {
                try {
                    inputBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public boolean checkLevelCompleted() {
        return false;
    }

}
