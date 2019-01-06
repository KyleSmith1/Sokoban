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
abstract class MapElement {

   

    public MapElement() {

        Coordinate objectCoords = new Coordinate();
        int xCoord = objectCoords.getX();
        int yCoord = objectCoords.getY();
        
        //objectCoords.setX(xCoord + 32);
        //objectCoords.setY(yCoord + 32);

    }

    public void displayImage() {

        Stage window = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 500);

        Scene appSceneGame = new Scene(gridPane);
        window.setScene(appSceneGame);
        window.show();

    }

    abstract public void createElement();

}
