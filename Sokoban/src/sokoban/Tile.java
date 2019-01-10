package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class Tile extends MapElement {

    public Tile() {
        this.elementType = "Floor";
    }

    public void createElement() {

        Image elementImage = new Image("file:resources/SokobanImages/Floor.png");
        ImageView im = new ImageView();
        im.setImage(elementImage);
        im.setLayoutX(70);
        this.elementImageView = im;

    }

}
