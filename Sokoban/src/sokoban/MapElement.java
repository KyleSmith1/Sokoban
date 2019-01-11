package sokoban;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

/**
 * @author 14001835
 */
abstract class MapElement {

    protected String elementType;
    public static AnchorPane anchorPane = new AnchorPane();
    public static GridPane gridPane = new GridPane();
    public static HBox topBorder = new HBox();
    public static Stage window = new Stage();

    public MapElement() {
        //Creates an instance of the coordinate class
        Coordinate objectCoords = new Coordinate();

    }

    public void displayImage(int x, int y) {

        //Calls the createElement() class and sets the result to an ImageView "im"
        ImageView im = createElement();

        //Sets the layout of the image where map[i][j] = map[x][y]. The x and y are multiplied by 32 as that is the width and height of the supplied images for each element.
        im.setLayoutX(x * 32);
        im.setLayoutY(y * 32);

        anchorPane.getChildren().add(im);

    }

    //Abstract class createElement()
    abstract public ImageView createElement();

    public void displayGame() {

        BorderPane borderPane = new BorderPane();
        borderPane.setMinSize(800, 500);

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        topBorder.setPadding(new Insets(10, 10, 10, 10));
        topBorder.setAlignment(Pos.CENTER);

        borderPane.setTop(topBorder);
        borderPane.setCenter(anchorPane);
        borderPane.setBottom(gridPane);

        topBorder.setMinSize(800, 100);
        gridPane.setMinSize(800, 100);

        //Window cannot be resized
        window.setResizable(false);

        Scene appSceneGame = new Scene(borderPane);
        window.setScene(appSceneGame);
        window.show();

    }

}
