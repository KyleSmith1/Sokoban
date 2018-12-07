package sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author 14001835
 */
public class Level {

    private MapElement map[] = new MapElement[1000];
    private int numberOfMoves;
    private WarehouseKeeper warehouseKeeper;
    private Crate crates[] = new Crate[10];

    public Level() {

    }

    public Level(int levelNumber) {

        //Stage window = new Stage();
        //window.setTitle("Sokoban Level " + levelNumber);

        //GridPane gridPane = new GridPane();

        //gridPane.setMinSize(800, 500);

        File inputFile = new File("resources/SokobanMaps/level" + levelNumber + ".txt");
        if (inputFile.exists()) {
            System.out.println("Found file textFile : " + inputFile.getName());
        } else {
            System.out.println("Cannot find file : " + inputFile.getAbsolutePath() + "/" + inputFile.getName());
        }

        FileReader reader = null;
        BufferedReader inputBuffer = null;

        try {
            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);
            int input = inputBuffer.read();

            while (input != -1) {
                char c = (char) input;
                //Tile
                if (c == ' ') {
                    Tile newFloor = new Tile();
                    for (int i = 0; i < map.length; i++) {
                        if (map[i] == null) {
                            map[i] = newFloor;
                            break;
                        }
                    }

                } //Wall
                else if (c == 'X') {
                    Wall newWall = new Wall();
                    for (int i = 0; i < map.length; i++) {
                        if (map[i] == null) {
                            map[i] = newWall;
                            break;
                        }
                    }

                } //Crate
                else if (c == '*') {
                    Crate newCrate = new Crate();
                    for (int i = 0; i < crates.length; i++) {
                        if (crates[i] == null) {
                            crates[i] = newCrate;
                            break;
                        }
                    }
                    for (int i = 0; i < map.length; i++) {
                        if (map[i] == null) {
                            map[i] = newCrate;
                            break;
                        }
                    }

                } //Diamond
                else if (c == '.') {
                    Diamond newDiamond = new Diamond();
                    for (int i = 0; i < map.length; i++) {
                        if (map[i] == null) {
                            map[i] = newDiamond;
                            break;
                        }
                    }

                } //Warehouse Keeper
                else if (c == '@') {
                    WarehouseKeeper newKeeper = new WarehouseKeeper();
                    for (int i = 0; i < map.length; i++) {
                        if (map[i] == null) {
                            map[i] = newKeeper;
                            break;
                        }
                    }

                }
                input = inputBuffer.read();

            }
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

        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                String tileType = map[i].displayImage();
                Image image = new Image("file:resources/SokobanImages/" + tileType);
                ImageView im = new ImageView();
                im.setImage(image);
                //for (int k = 0; k < 16; k++) {
                    //for (int j = 0; j < 26; j++) {
                        
                        //gridPane.add(im, j, k);
                        System.out.println(map[i].displayImage());
                        
                    //}
                //}

            }
        }
        
        //Scene appSceneGame = new Scene(gridPane);
       // window.setScene(appSceneGame);
       // window.show();

    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public boolean checkLevelCompleted() {
        return false;
    }

}
