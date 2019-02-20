package sokoban;

/**
 * @author 14001835
 */
public class Tile extends MapElement {

    public Tile() {
        
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {
        this.elementType = "Floor";
        objectCoords.setX(x * 32);
        objectCoords.setY(y * 32);

    }

}
