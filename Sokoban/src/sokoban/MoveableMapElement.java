package sokoban;

/**
 * @author 14001835
 */
abstract class MoveableMapElement extends MapElement {

    private Coordinate position;

    //Moveable map elements also inherit the moveElement class
    //Changes the element's co-ords
    public void moveElement(Coordinate newCoord) {
        
        position = newCoord;
        
        this.objectCoords.setX(position.getX());
        this.objectCoords.setY(position.getY());
        this.displayImage();
        
    }

}
