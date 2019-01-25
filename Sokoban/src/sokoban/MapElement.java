package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
abstract class MapElement {

    //elementType is floor as default, changed depending on the tile
    protected String elementType = "Floor";
    protected Coordinate objectCoords;

    public MapElement() {
       

    }

    //Adds the element's image to the window
    public void displayImage() {

        Image elementImage = new Image("file:resources/SokobanImages/" + elementType + ".png");
        ImageView im = new ImageView();
        im.setImage(elementImage);

        //Sets the layout of the image
        im.setLayoutX(objectCoords.getX());
        im.setLayoutY(objectCoords.getY());

        SokobanGame.anchorPane.getChildren().add(im);

    }

    //Abstract class createElement()
    abstract public void createElement(int x, int y);

}
