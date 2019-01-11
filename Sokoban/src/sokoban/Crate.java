package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class Crate extends MoveableMapElement {

    public Crate() {
        this.elementType = "Crate";
    }

    //Return the ImageView for the crate image
    public ImageView createElement() {

        Image elementImage = new Image("file:resources/SokobanImages/Crate.png");
        ImageView im = new ImageView();
        im.setImage(elementImage);
        return im;

    }

}
