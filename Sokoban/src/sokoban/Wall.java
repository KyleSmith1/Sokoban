package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class Wall extends MapElement {

    public Wall() {
        this.elementType = "Wall";
    }

    //Return the ImageView for the wall image
    public ImageView createElement() {

        Image elementImage = new Image("file:resources/SokobanImages/Wall.png");
        ImageView im = new ImageView();
        im.setImage(elementImage);
        return im;
    }

}
