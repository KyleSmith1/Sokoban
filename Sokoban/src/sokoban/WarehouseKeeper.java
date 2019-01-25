package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class WarehouseKeeper extends MoveableMapElement {

    public WarehouseKeeper() {
        this.elementType = "WarehouseKeeper";
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {

        objectCoords.setX(x*32);
        objectCoords.setY(y*32);
        
    }

}
