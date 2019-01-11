package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class WarehouseKeeper extends MoveableMapElement {

    public WarehouseKeeper() {
        this.elementType = "WarehouseKeeper";
    }

    public ImageView createElement() {

        Image elementImage = new Image("file:resources/SokobanImages/WarehouseKeeper.png");
        ImageView im = new ImageView();
        im.setImage(elementImage);
        return im;
    }

}
