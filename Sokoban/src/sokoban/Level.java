package sokoban;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author 14001835
 */
public class Level {

    private MapElement map[][] = new MapElement[25][15];
    private int numberOfMoves;
    private WarehouseKeeper warehouseKeeper;
    private Crate crates[] = new Crate[10];

    public Level() {

    }

    public Level(int levelNumber) {

        File inputFile = new File("resources/SokobanMaps/level" + levelNumber + ".txt");

        FileReader reader = null;
        BufferedReader inputBuffer = null;

        try {

            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);
            int input = inputBuffer.read();
            while (input != -1) {
                //Create system where i = x and j = y and times that by 32 to get x and y co-ords for the display

                //Get variable from other classes class.variable?
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {

                        char c = (char) input;
                        //Tile
                        if (c == ' ') {
                            Tile newFloor = new Tile();
                            if (map[i][j] == null) {
                                map[i][j] = newFloor;
                                newFloor.createElement();
                                newFloor.displayImage();
                                break;
                            }

                        } //Wall
                        else if (c == 'X') {
                            Wall newWall = new Wall();
                            if (map[i][j] == null) {
                                map[i][j] = newWall;
                                newWall.createElement();
                                newWall.displayImage();
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
                                newCrate.createElement();
                                newCrate.displayImage();
                                break;
                            }

                        } //Diamond
                        else if (c == '.') {
                            Diamond newDiamond = new Diamond();
                            if (map[i][j] == null) {
                                map[i][j] = newDiamond;
                                newDiamond.createElement();
                                newDiamond.displayImage();
                                break;
                            }

                        } //Warehouse Keeper
                        else if (c == '@') {
                            WarehouseKeeper newKeeper = new WarehouseKeeper();
                            if (map[i][j] == null) {
                                map[i][j] = newKeeper;
                                newKeeper.createElement();
                                warehouseKeeper = newKeeper;
                                warehouseKeeper.displayImage();
                                break;
                            }
                        }

                    }

                    input = inputBuffer.read();
                }

            }
            
            map[0][0].displayGame();

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
