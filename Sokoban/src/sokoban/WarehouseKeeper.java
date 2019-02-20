package sokoban;


/**
 * @author 14001835
 */
public class WarehouseKeeper extends MoveableMapElement {

    public WarehouseKeeper() {
        
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {
        this.elementType = "WarehouseKeeper";
        objectCoords.setX(x*32);
        objectCoords.setY(y*32);
        
    }

}
