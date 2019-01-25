package sokoban;


/**
 * @author 14001835
 */
public class Crate extends MoveableMapElement {

    public Crate() {
        this.elementType = "Crate";
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {

        objectCoords.setX(x*32);
        objectCoords.setY(y*32);

    }

}
