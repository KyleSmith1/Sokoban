package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class Diamond extends MapElement {

    private Boolean hasCrate;

    public Diamond() {
        this.elementType = "Diamond";
    }

    public ImageView createElement() {

        Image elementImage = new Image("file:resources/SokobanImages/Diamond.png");
        ImageView im = new ImageView();
        im.setImage(elementImage);
        return im;

    }

    public void setHasCrate(boolean crateStateChange) {
        hasCrate = crateStateChange;
    }

    public boolean getHasCrate() {
        return hasCrate;
    }
}
