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
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {

        objectCoords.setX(x * 32);
        objectCoords.setY(y * 32);

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
