package sokoban;

/**
 * @author 14001835
 */
abstract class MoveableMapElement extends MapElement {

    private Coordinate position;

    //moveable map elements also inherit the moveElement class
    public void moveElement(Coordinate newCoord) {
        position = newCoord;
    }

}
