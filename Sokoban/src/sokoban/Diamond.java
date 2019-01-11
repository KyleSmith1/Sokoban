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

    //Return the ImageView for the diamond image
    public ImageView createElement() {

        Image elementImage = new Image("file:resources/SokobanImages/Diamond.png");
        ImageView im = new ImageView();
        im.setImage(elementImage);
        return im;

    }

    //If the diamond has a crate on top of it, set "hasCrate" is changed to true and vice versa. 
    public void setHasCrate(boolean crateStateChange) {
        hasCrate = crateStateChange;
    }

    //Detemine whether or not the diamond currently has a crate on it
    public boolean getHasCrate() {
        return hasCrate;
    }
}
