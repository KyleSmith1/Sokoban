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
        System.out.println("Error. Level not selected.");
    }

    public Level(int levelNumber) {

        //Finds the file for the chosen level number
        File inputFile = new File("resources/SokobanMaps/level" + levelNumber + ".txt");

        FileReader reader = null;
        BufferedReader inputBuffer = null;

        //Sets the title for the window
        MapElement.window.setTitle("Sokoban Level " + levelNumber);

        //Creates a second reader and buffered reader to get the length of a line for that level without impacting the original reader/buffered reader which displays the map
        FileReader reader2 = null;
        BufferedReader inputBuffer2 = null;

        Label levelLabel = new Label("Level " + levelNumber);
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

        //Adds the controller buttons to the gridpane
        gridPane.add(upButton, 2, 1);
        gridPane.add(downButton, 2, 2);
        gridPane.add(leftButton, 1, 2);
        gridPane.add(rightButton, 3, 2);

        try {

            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);

            reader2 = new FileReader(inputFile);
            inputBuffer2 = new BufferedReader(reader2);
            String inputLine = inputBuffer2.readLine();
            int lineLength = inputLine.length();

            //Map.length is always 25 (Size of the map[i] array). 
            int lineLengthModifier = (map.length - lineLength) - 2;
            
            //Reads the level file one character at a time
            int input = inputBuffer.read();

            //Until there are no more characters to read
            while (input != -1) {
              
                //lineLengthModifier is subtracted from map.length so that the map displays correctly.
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
                    //Read the next character and repeat the whole process
                    input = inputBuffer.read();
                }

            }

            //Actions triggered by the "up" button
            upButton.setOnAction(value -> {
                
                //Iterate through the entire map again
                for (int i = 0; i < map.length - lineLengthModifier; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        //Find the location of the warehouse keeper
                        if (map[i][j] == warehouseKeeper) {
                            //If the element one space up from the keeper is not a wall...
                            if (!(map[i][j - 1] instanceof Wall)) {
                                //If the element one space up from the keeper is a crate...
                                if ((map[i][j - 1] instanceof Crate)) {
                                    //And if the element two spaces up from the keeper (and one space up from the crate) is not another crate or a wall...
                                    if (!(map[i][j - 2] instanceof Crate || map[i][j - 2] instanceof Wall)) {
                                        //Replace the space one up from the crate with a crate
                                        Crate newCrate = new Crate();
                                        map[i][j - 2] = newCrate;
                                        newCrate.displayImage(i, j - 2);
                                    } else {
                                        break;
                                    }
                                }
                                //If the 2d array "diamonds" is not empty at [i][j], that means there is a diamond at that space on the map. The diamonds have their own array so that their position never changes even when a crate or a keeper is on top of it.
                                if (diamonds[i][j] != null) {
                                    //As the keeper moves a space up, replace the tile the keeper was on with a diamond once again.
                                    Diamond newDiamond = new Diamond();
                                    map[i][j] = newDiamond;
                                    newDiamond.displayImage(i, j);
                                } else {
                                    //If the tile wasn't originally a diamond, replace it with a regular floor.
                                    Tile newFloor = new Tile();
                                    map[i][j] = newFloor;
                                    newFloor.displayImage(i, j);
                                }
                                //Set the keeper to the space one up from the original position
                                map[i][j - 1] = warehouseKeeper;
                                warehouseKeeper.displayImage(i, j - 1);
                                numberOfMoves++;
                                //Refresh the number of moves label
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }

                        }
                    }
                }

            });

            downButton.setOnAction(value -> {

                //Same as up button but for down
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
                                //The 'j' variable is skipped forward 2 spaces otherwise certain actions repeat. For example, the keeper at [i][j] moves to [i][j+1] and then the for loop continues to the next in the array and the actions repeats itself until the keeper cannot move anymore
                                j = j + 2;
                                numberOfMoves++;
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }
                        }
                    }
                }
            });

            //Same as up button except moves one space to the left
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

            //Same as up button but moves on space to the right
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
                                //i is skipped forward for the same reason that j is skipped forward when moving down
                                i = i + 2;
                                numberOfMoves++;
                                movesLabel.setText("Number of Moves: " + numberOfMoves);
                            }
                        }
                    }
                }

            });

            //Displays the window in which the game is played.
            map[0][0].displayGame();

            //Error handling for the file reader
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
    
    //Gets the number of moves
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    //Checks if the level is completed
    public boolean checkLevelCompleted() {
        return false;
    }

}
