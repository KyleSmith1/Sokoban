package sokoban;


/**
 * @author 14001835
 */
public class Wall extends MapElement {

    public Wall() {
        
        this.objectCoords = new Coordinate();
    }

    //Gives the element an x and y co-ordinate
    public void createElement(int x, int y) {
        this.elementType = "Wall";
        objectCoords.setX(x*32);
        objectCoords.setY(y*32);
        
    }

}
