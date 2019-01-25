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
import static sokoban.SokobanGame.gridPane;

/**
 * @author 14001835
 */
public class Level {

    private MapElement map[][] = new MapElement[25][25];
    private int numberOfMoves = 0;
    private WarehouseKeeper warehouseKeeper;
    private Crate crates[] = new Crate[10];
    private Diamond diamonds[][] = new Diamond[25][25];
    private int currentLevel;
    private int lineLength;

    //If no level is selected, game defaults to level 1
    public Level() {
        System.out.println("Error. Level not selected. Defaulting to Level 1");
        currentLevel = 1;
        displayLevel();
    }

    //Sets the current level to the level selected and displays
    public Level(int levelNumber) {
        currentLevel = levelNumber;
        displayLevel();
    }

    //Reads the level file and displays to screen
    public void displayLevel() {

        //Finds the file for the chosen level number
        File inputFile = new File("resources/SokobanMaps/level" + currentLevel + ".txt");

        FileReader reader = null;
        BufferedReader inputBuffer = null;

        //Sets the title for the window
        SokobanGame.window.setTitle("Sokoban Level " + currentLevel);

        Label levelLabel = new Label("Level " + currentLevel);
        Label movesLabel = new Label("Number of Moves: " + numberOfMoves);

        movesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        levelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        movesLabel.setTranslateX(100);
        SokobanGame.topBorder.getChildren().add(levelLabel);
        SokobanGame.topBorder.getChildren().add(movesLabel);

        try {

            reader = new FileReader(inputFile);
            inputBuffer = new BufferedReader(reader);

            String input = inputBuffer.readLine();
            lineLength = input.length();

            //Reads the level file one line at a time
            //Until there are no more lines to read
            while (input != null) {

                //Split line into individual characters
                char[] lineChars = input.toCharArray();
                for (int i = 0; i < lineLength; i++) {
                    for (int j = 0; j < map[i].length; j++) {

                        //Tile
                        if (lineChars[i] == ' ') {
                            Tile newFloor = new Tile();
                            if (map[i][j] == null) {
                                map[i][j] = newFloor;
                                newFloor.createElement(i, j);
                                newFloor.displayImage();
                                break;
                            }

                        } //Wall
                        else if (lineChars[i] == 'X') {
                            Wall newWall = new Wall();
                            if (map[i][j] == null) {
                                map[i][j] = newWall;

                                newWall.createElement(i, j);
                                newWall.displayImage();

                                break;
                            }

                        } //Crate
                        else if (lineChars[i] == '*') {
                            Crate newCrate = new Crate();
                            for (int k = 0; k < crates.length; k++) {
                                if (crates[k] == null) {
                                    crates[k] = newCrate;
                                    break;
                                }
                            }

                            if (map[i][j] == null) {
                                map[i][j] = newCrate;
                                newCrate.createElement(i, j);
                                newCrate.displayImage();
                                break;
                            }

                        } //Diamond
                        else if (lineChars[i] == '.') {
                            Diamond newDiamond = new Diamond();
                            if (map[i][j] == null) {
                                map[i][j] = newDiamond;
                                diamonds[i][j] = newDiamond;
                                newDiamond.createElement(i, j);
                                newDiamond.displayImage();
                                break;
                            }

                        } //Warehouse Keeper
                        else if (lineChars[i] == '@') {
                            WarehouseKeeper newKeeper = new WarehouseKeeper();
                            warehouseKeeper = newKeeper;
                            if (map[i][j] == null) {
                                map[i][j] = warehouseKeeper;
                                warehouseKeeper.createElement(i, j);
                                warehouseKeeper.displayImage();

                                break;
                            }

                        }
                    }

                }

                //Read the next character and repeat the whole process
                input = inputBuffer.readLine();

            }

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

    public void controlKeeper(String direction) {

        Coordinate newCoord = new Coordinate();

        //Iterate through the entire map again
        for (int i = 0; i < lineLength; i++) {
            for (int j = 0; j < map[i].length; j++) {
                //Find the location of the warehouse keeper
                if (map[i][j] == warehouseKeeper) {

                    //Actions triggered by the "up" button
                    if (direction.equals("Up")) {
                        //If the element one space up from the keeper is not a wall...
                        if (!(map[i][j - 1] instanceof Wall)) {
                            //If the element one space up from the keeper is a crate...
                            if ((map[i][j - 1] instanceof Crate)) {
                                //And if the element two spaces up from the keeper (and one space up from the crate) is not another crate or a wall...
                                if (!(map[i][j - 2] instanceof Crate || map[i][j - 2] instanceof Wall)) {
                                    //Replace the space one up from the crate with a crate
                                    Crate newCrate = new Crate();
                                    newCrate = (Crate) map[i][j - 1];
                                    map[i][j - 2] = map[i][j - 1];
                                    newCoord.setX(warehouseKeeper.objectCoords.getX());
                                    newCoord.setY((warehouseKeeper.objectCoords.getY()) - 64);
                                    newCrate.moveElement(newCoord);

                                } else {
                                    break;
                                }
                            }

                            //Set the keeper to the space one up from the original position
                            map[i][j - 1] = warehouseKeeper;
                            newCoord.setX(warehouseKeeper.objectCoords.getX());
                            newCoord.setY((warehouseKeeper.objectCoords.getY()) - 32);

                            //If the 2d array "diamonds" is not empty at [i][j], that means there is a diamond at that space on the map. The diamonds have their own array so that their position never changes even when a crate or a keeper is on top of it.
                            if (diamonds[i][j] != null) {
                                //As the keeper moves a space up, replace the tile the keeper was on with a diamond once again.
                                Diamond newDiamond = new Diamond();
                                map[i][j] = newDiamond;
                                newDiamond.createElement(i, j);
                                newDiamond.displayImage();
                            } else {
                                //If the tile wasn't originally a diamond, replace it with a regular floor.
                                Tile newFloor = new Tile();
                                map[i][j] = newFloor;
                                newFloor.createElement(i, j);
                                newFloor.displayImage();
                            }

                            warehouseKeeper.moveElement(newCoord);
                            numberOfMoves++;

                            //Refresh the number of moves label
                            //movesLabel.setText("Number of Moves: " + numberOfMoves);
                        }

                    }

                    //Same as up button but for down
                    if (direction.equals("Down")) {

                        if (!(map[i][j + 1] instanceof Wall)) {
                            if ((map[i][j + 1] instanceof Crate)) {
                                if (!(map[i][j + 2] instanceof Crate || map[i][j + 2] instanceof Wall)) {
                                    Crate newCrate = new Crate();
                                    newCrate = (Crate) map[i][j + 1];
                                    map[i][j + 2] = map[i][j + 1];
                                    newCoord.setX(warehouseKeeper.objectCoords.getX());
                                    newCoord.setY((warehouseKeeper.objectCoords.getY()) + 64);
                                    newCrate.moveElement(newCoord);

                                } else {
                                    break;
                                }
                            }

                            map[i][j + 1] = warehouseKeeper;
                            newCoord.setX(warehouseKeeper.objectCoords.getX());
                            newCoord.setY((warehouseKeeper.objectCoords.getY()) + 32);

                            //If the 2d array "diamonds" is not empty at [i][j], that means there is a diamond at that space on the map. The diamonds have their own array so that their position never changes even when a crate or a keeper is on top of it.
                            if (diamonds[i][j] != null) {
                                //As the keeper moves a space up, replace the tile the keeper was on with a diamond once again.
                                Diamond newDiamond = new Diamond();
                                map[i][j] = newDiamond;
                                newDiamond.createElement(i, j);
                                newDiamond.displayImage();
                            } else {
                                //If the tile wasn't originally a diamond, replace it with a regular floor.
                                Tile newFloor = new Tile();
                                map[i][j] = newFloor;
                                newFloor.createElement(i, j);
                                newFloor.displayImage();
                            }

                            warehouseKeeper.moveElement(newCoord);
                            numberOfMoves++;

                            //The 'j' variable is skipped forward 2 spaces otherwise certain actions repeat. For example, the keeper at [i][j] moves to [i][j+1] and then the for loop continues to the next in the array and the actions repeats itself until the keeper cannot move anymore
                            j = j + 2;

                            //movesLabel.setText("Number of Moves: " + numberOfMoves);
                        }
                    }

                    //Same as up button except moves one space to the left
                    if (direction.equals("Left")) {

                        if (!(map[i - 1][j] instanceof Wall)) {
                            if ((map[i - 1][j] instanceof Crate)) {
                                if (!(map[i - 2][j] instanceof Crate || map[i - 2][j] instanceof Wall)) {
                                    Crate newCrate = new Crate();
                                    newCrate = (Crate) map[i - 1][j];
                                    map[i - 2][j] = map[i - 1][j];
                                    newCoord.setX(warehouseKeeper.objectCoords.getX() - 64);
                                    newCoord.setY(warehouseKeeper.objectCoords.getY());
                                    newCrate.moveElement(newCoord);

                                } else {
                                    break;
                                }
                            }

                            map[i - 1][j] = warehouseKeeper;
                            newCoord.setX(warehouseKeeper.objectCoords.getX() - 32);
                            newCoord.setY(warehouseKeeper.objectCoords.getY());

                            //If the 2d array "diamonds" is not empty at [i][j], that means there is a diamond at that space on the map. The diamonds have their own array so that their position never changes even when a crate or a keeper is on top of it.
                            if (diamonds[i][j] != null) {
                                //As the keeper moves a space up, replace the tile the keeper was on with a diamond once again.
                                Diamond newDiamond = new Diamond();
                                map[i][j] = newDiamond;
                                newDiamond.createElement(i, j);
                                newDiamond.displayImage();
                            } else {
                                //If the tile wasn't originally a diamond, replace it with a regular floor.
                                Tile newFloor = new Tile();
                                map[i][j] = newFloor;
                                newFloor.createElement(i, j);
                                newFloor.displayImage();
                            }

                            warehouseKeeper.moveElement(newCoord);
                            numberOfMoves++;

                            //movesLabel.setText("Number of Moves: " + numberOfMoves);
                        }
                    }

                    //Same as up button but moves on space to the right
                    if (direction.equals("Right")) {

                        if (!(map[i + 1][j] instanceof Wall)) {
                            if ((map[i + 1][j] instanceof Crate)) {
                                if (!(map[i + 2][j] instanceof Crate || map[i + 2][j] instanceof Wall)) {
                                    Crate newCrate = new Crate();
                                    newCrate = (Crate) map[i + 1][j];
                                    map[i + 2][j] = map[i + 1][j];
                                    newCoord.setX(warehouseKeeper.objectCoords.getX() + 64);
                                    newCoord.setY(warehouseKeeper.objectCoords.getY());
                                    newCrate.moveElement(newCoord);

                                } else {
                                    break;
                                }
                            }

                            map[i + 1][j] = warehouseKeeper;
                            newCoord.setX(warehouseKeeper.objectCoords.getX() + 32);
                            newCoord.setY(warehouseKeeper.objectCoords.getY());

                            //If the 2d array "diamonds" is not empty at [i][j], that means there is a diamond at that space on the map. The diamonds have their own array so that their position never changes even when a crate or a keeper is on top of it.
                            if (diamonds[i][j] != null) {
                                //As the keeper moves a space up, replace the tile the keeper was on with a diamond once again.
                                Diamond newDiamond = new Diamond();
                                map[i][j] = newDiamond;
                                newDiamond.createElement(i, j);
                                newDiamond.displayImage();
                            } else {
                                //If the tile wasn't originally a diamond, replace it with a regular floor.
                                Tile newFloor = new Tile();
                                map[i][j] = newFloor;
                                newFloor.createElement(i, j);
                                newFloor.displayImage();
                            }

                            warehouseKeeper.moveElement(newCoord);
                            numberOfMoves++;

                            //i is skipped forward for the same reason that j is skipped forward when moving down
                            i = i + 2;

                            //movesLabel.setText("Number of Moves: " + numberOfMoves);
                        }
                    }

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
