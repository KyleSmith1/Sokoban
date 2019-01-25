package sokoban;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 14001835
 */
public class Wall extends MapElement {

    public Wall() {
        this.elementType = "Wall";
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {

        objectCoords.setX(x*32);
        objectCoords.setY(y*32);
        
    }

}
