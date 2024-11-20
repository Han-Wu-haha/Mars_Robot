package vo;

/**
 * Represents a 2D point or location with x and y coordinates. This class is useful for 
 * modeling spatial entities in a two-dimensional space or grid.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class Location {

    // The x-coordinate of the location
    private int x = 0;

    // The y-coordinate of the location
    private int y = 0;

    /**
     * Constructs a new Location object with specified x and y coordinates.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the x-coordinate of the location.
     * 
     * @return The x-coordinate.
     */
    public int x() {
        return x;
    }

    /**
     * Sets the x-coordinate of the location.
     * 
     * @param x The new x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the y-coordinate of the location.
     * 
     * @return The y-coordinate.
     */
    public int y() {
        return y;
    }

    /**
     * Sets the y-coordinate of the location.
     * 
     * @param y The new y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }
}

