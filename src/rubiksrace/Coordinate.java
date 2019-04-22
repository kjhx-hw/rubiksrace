/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package rubiksrace;

/**
 *
 * @author kjhuggins
 */
public class Coordinate {
    int x = 0;
    int y = 0;
    
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinate(Coordinate coordinate) {
        this.x = coordinate.x;
        this.y = coordinate.y;
    }
    
    @Override
    public String toString() {
        return x + ", " + y;
    }
}
