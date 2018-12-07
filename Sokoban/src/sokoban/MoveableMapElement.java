package sokoban;

/**
 * @author 14001835
 */
abstract class MoveableMapElement extends MapElement {

    private Coordinate position;
    
    abstract public void moveElement(Coordinate newCoord);
    
}
