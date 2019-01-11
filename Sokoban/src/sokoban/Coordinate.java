package sokoban;

/**
 * @author 14001835
 */
public class Coordinate {

    private int xCoord;
    private int yCoord;

    public Coordinate() {
        this.xCoord = getX();
        this.yCoord = getY();
    }

    //Returns the x co-ordinate
    public int getX() {
        return xCoord;
    }

    //Returns the y co-ordinate
    public int getY() {
        return yCoord;
    }

    //Set the value for the x co-ordinate
    public void setX(int newX) {
        xCoord = newX;
    }

    //Set the value for the y co-ordinate
    public void setY(int newY) {
        yCoord = newY;
    }

}
