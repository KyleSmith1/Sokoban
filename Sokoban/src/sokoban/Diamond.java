package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class Diamond extends MapElement {

    private Boolean hasCrate;

    public Diamond() {

    }

    public void createElement() {
        /*
        Image diamondImage = new Image("file:resources/SokobanImages/Diamond.png");
        ImageView im = new ImageView();
        im.setImage(diamondImage);

        im.setLayoutX(xCoord);
        im.setLayoutY(yCoord);
        */
    }

    public void setHasCrate(boolean crateStateChange) {
        hasCrate = crateStateChange;
    }

    public boolean getHasCrate() {
        return hasCrate;
    }
}
