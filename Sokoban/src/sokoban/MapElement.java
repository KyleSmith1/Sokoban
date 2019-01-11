package sokoban;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private int xCoord;
    private int yCoord;
    public static AnchorPane anchorPane = new AnchorPane();
    public static GridPane gridPane = new GridPane();
    public static HBox topBorder = new HBox();

    public MapElement() {

        Coordinate objectCoords = new Coordinate();
        xCoord = objectCoords.getX();
        yCoord = objectCoords.getY();

    }

    public void displayImage(int x, int y) {

        ImageView im = createElement();

        im.setLayoutX(x * 32);
        im.setLayoutY(y * 32);

        xCoord = x * 32;
        yCoord = y * 32;

        anchorPane.getChildren().add(im);

    }

    abstract public ImageView createElement();

    public void displayGame() {

        Stage window = new Stage();
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

        window.setResizable(false);

        Scene appSceneGame = new Scene(borderPane);
        window.setScene(appSceneGame);
        window.show();

    }

}
