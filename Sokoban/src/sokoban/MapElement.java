package sokoban;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author 14001835
 */
abstract class MapElement {

    protected String elementType;
    protected ImageView elementImageView;
    private AnchorPane anchorPane = new AnchorPane();

    public MapElement() {

        Coordinate objectCoords = new Coordinate();
        int xCoord = objectCoords.getX();
        int yCoord = objectCoords.getY();

        //objectCoords.setX(xCoord + 32);
        //objectCoords.setY(yCoord + 32);
    }

    public void displayImage() {
        
        anchorPane.getChildren().add(elementImageView);

    }

    abstract public void createElement();
    
    public void displayGame(){
        
        Stage window = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setMinSize(800, 500);

        HBox topBorder = new HBox();
        //AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        topBorder.setPadding(new Insets(10, 10, 10, 10));
        topBorder.setAlignment(Pos.CENTER);

        Label label = new Label("Level");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        topBorder.getChildren().add(label);

        borderPane.setTop(topBorder);
        borderPane.setCenter(anchorPane);
        borderPane.setBottom(gridPane);

        Scene appSceneGame = new Scene(borderPane);
        window.setScene(appSceneGame);
        window.show();

        Button upButton = new Button("\u2191");
        Button downButton = new Button("\u2193");
        Button leftButton = new Button("\u2190");
        Button rightButton = new Button("\u2192");

        gridPane.add(upButton, 2, 1);
        gridPane.add(downButton, 2, 2);
        gridPane.add(leftButton, 1, 2);
        gridPane.add(rightButton, 3, 2);

        upButton.setOnAction(value -> {
            //warehouseKeeper.moveElement();
        });

        downButton.setOnAction(value -> {
            //warehouseKeeper.moveElement(1);
        });

        leftButton.setOnAction(value -> {
            //warehouseKeeper.moveElement(1);
        });

        rightButton.setOnAction(value -> {
            //warehouseKeeper.moveElement(1);
        });
    }

}
