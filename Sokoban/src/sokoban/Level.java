package sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import static sokoban.MapElement.gridPane;

/**
 * @author 14001835
 */
public class Level {

    private MapElement map[][] = new MapElement[25][15];
    private int numberOfMoves = 0;
    private WarehouseKeeper warehouseKeeper;
    private Crate crates[] = new Crate[10];
    private Diamond diamonds[][] = new Diamond[25][15];

    public Level() {

    }

    public Level(int levelNumber) {

        File inputFile = new File("resources/SokobanMaps/level" + levelNumber + ".txt");

        FileReader reader = null;
        BufferedReader inputBuffer = null;

        FileReader reader2 = null;
        BufferedReader inputBuffer2 = null;
        
        Label levelLabel= new Label("Level " + levelNumber);
        Label movesLabel = new Label("Number of Moves: " + numberOfMoves);
        
        movesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        levelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        movesLabel.setTranslateX(100);
        MapElement.topBorder.getChildren().add(levelLabel);
        MapElement.topBorder.getChildren().add(movesLabel);

        Button upButton = new Button("\u2191");
        Button downButton = new Button("\u2193");
        Button leftButton = new Button("\u2190");
        Button rightButton = new Button("\u2192");
        Button resetButton = new Button("Reset Level");
        Button nextLevelButton = new Button("Next level");
        Label emptyLabel = new Label("               ");

        gridPane.add(upButton, 2, 1);
        gridPane.add(downButton, 2, 2);
        gridPane.add(leftButton, 1, 2);
        gridPane.add(rightButton, 3, 2);
        gridPane.add(emptyLabel, 4, 2);
        gridPane.add(resetButton, 5, 2);
        gridPane.add(nextLevelButton, 5, 1);

        try {

            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);

            reader2 = new FileReader(inputFile);
            inputBuffer2 = new BufferedReader(reader2);
            String inputLine = inputBuffer2.readLine();
            int lineLength = inputLine.length();

            int lineLengthModifier = (map.length - lineLength) - 2;

            int input = inputBuffer.read();

            while (input != -1) {
                //Create system where i = x and j = y and times that by 32 to get x and y co-ords for the display

                //Get variable from other classes class.variable?
                for (int i = 0; i < map.length - lineLengthModifier; i++) {
                    for (int j = 0; j < map[i].length; j++) {

                        char c = (char) input;
                        //Tile
                        if (c == ' ') {
                            Tile newFloor = new Tile();
                            if (map[i][j] == null) {
                                map[i][j] = newFloor;

                                newFloor.displayImage(i, j);
                                break;
                            }

                        } //Wall
                        else if (c == 'X') {
                            Wall newWall = new Wall();
                            if (map[i][j] == null) {
                                map[i][j] = newWall;

                                newWall.displayImage(i, j);

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

                                newCrate.displayImage(i, j);
                                break;
                            }

                        } //Diamond
                        else if (c == '.') {
                            Diamond newDiamond = new Diamond();
                            if (map[i][j] == null) {
                                map[i][j] = newDiamond;
                                diamonds[i][j] = newDiamond;
                                newDiamond.displayImage(i, j);
                                break;
                            }

                        } //Warehouse Keeper
                        else if (c == '@') {
                            WarehouseKeeper newKeeper = new WarehouseKeeper();
                            warehouseKeeper = newKeeper;
                            if (map[i][j] == null) {
                                map[i][j] = warehouseKeeper;

                                warehouseKeeper.displayImage(i, j);

                                break;
                            }
                        }

                    }

                    input = inputBuffer.read();
                }

            }

            upButton.setOnAction(value -> {

                for (int i = 0; i < map.length - lineLengthModifier; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == warehouseKeeper) {
                            if (!(map[i][j - 1] instanceof Wall)) {
                                if ((map[i][j - 1] instanceof Crate)) {
                                    if (!(map[i][j - 2] instanceof Crate || map[i][j - 2] instanceof Wall)) {
                                        Crate newCrate = new Crate();
                                        map[i][j - 2] = newCrate;
                                        newCrate.displayImage(i, j - 2);
                                    } else {
                                        break;
                                    }
                                }
                                if (diamonds[i][j] != null) {
                                    Diamond newDiamond = new Diamond();
                                    map[i][j] = newDiamond;
                                    newDiamond.displayImage(i, j);
                                } else {
                                    Tile newFloor = new Tile();
                                    map[i][j] = newFloor;
                                    newFloor.displayImage(i, j);
                                }
                                map[i][j - 1] = warehouseKeeper;
                                warehouseKeeper.displayImage(i, j - 1);
                                numberOfMoves++;
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }

                        }
                    }
                }

            });

            downButton.setOnAction(value -> {

                for (int i = 0; i < map.length - lineLengthModifier; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == warehouseKeeper) {
                            if (!(map[i][j + 1] instanceof Wall)) {
                                if ((map[i][j + 1] instanceof Crate)) {
                                    if (!(map[i][j + 2] instanceof Crate || map[i][j + 2] instanceof Wall)) {
                                        Crate newCrate = new Crate();
                                        map[i][j + 2] = newCrate;
                                        newCrate.displayImage(i, j + 2);
                                    } else {
                                        break;
                                    }
                                }
                                if (diamonds[i][j] != null) {
                                    Diamond newDiamond = new Diamond();
                                    map[i][j] = newDiamond;
                                    newDiamond.displayImage(i, j);

                                } else {

                                    Tile newFloor = new Tile();
                                    map[i][j] = newFloor;
                                    newFloor.displayImage(i, j);

                                }
                                map[i][j + 1] = warehouseKeeper;
                                warehouseKeeper.displayImage(i, j + 1);
                                j = j + 3;
                                numberOfMoves++;
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }
                        }
                    }
                }
            });

            leftButton.setOnAction(value -> {

                for (int i = 0; i < map.length - lineLengthModifier; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == warehouseKeeper) {
                            if (!(map[i - 1][j] instanceof Wall)) {
                                if ((map[i - 1][j] instanceof Crate)) {
                                    if (!(map[i - 2][j] instanceof Crate || map[i - 2][j] instanceof Wall)) {
                                        Crate newCrate = new Crate();
                                        map[i - 2][j] = newCrate;
                                        newCrate.displayImage(i - 2, j);
                                    } else {
                                        break;
                                    }
                                }
                                if (diamonds[i][j] != null) {
                                    Diamond newDiamond = new Diamond();
                                    map[i][j] = newDiamond;
                                    newDiamond.displayImage(i, j);
                                } else {

                                    Tile newFloor = new Tile();
                                    map[i][j] = newFloor;
                                    newFloor.displayImage(i, j);
                                }
                                map[i - 1][j] = warehouseKeeper;
                                warehouseKeeper.displayImage(i - 1, j);
                                numberOfMoves++;
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }
                        }
                    }
                }
            });

            rightButton.setOnAction(value -> {

                for (int i = 0; i < map.length - lineLengthModifier; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == warehouseKeeper) {
                            if (!(map[i + 1][j] instanceof Wall)) {
                                if ((map[i + 1][j] instanceof Crate)) {
                                    if (!(map[i + 2][j] instanceof Crate || map[i + 2][j] instanceof Wall)) {
                                        Crate newCrate = new Crate();
                                        map[i + 2][j] = newCrate;
                                        newCrate.displayImage(i + 2, j);
                                    } else {
                                        break;
                                    }
                                }
                                if (diamonds[i][j] != null) {
                                    Diamond newDiamond = new Diamond();
                                    map[i][j] = newDiamond;
                                    newDiamond.displayImage(i, j);

                                } else {

                                    Tile newFloor = new Tile();
                                    map[i][j] = newFloor;
                                    newFloor.displayImage(i, j);

                                }
                                map[i + 1][j] = warehouseKeeper;
                                warehouseKeeper.displayImage(i + 1, j);
                                i = i + 3;
                                numberOfMoves++;
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }
                        }
                    }
                }

            });

            resetButton.setOnAction(value -> {

            });

            nextLevelButton.setOnAction(value -> {

            });

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
