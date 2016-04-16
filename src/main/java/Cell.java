/**
 * Created by Michal Dolnak on 13.4.2016.
 *
 * Cell contains information about position on map.
 * There are 4 pieces of information available:
 * 1. Boolean rock - whether position is blocked by some object
 * 2. int rock_number - Number of rock on the map(starting with zero)
 * 3. Boolean visited - whether position was already visite by someone
 * 4. int visitCount - from which starting position has monk visited this cell
 */


public class Cell {
    Boolean rock;
    int rock_number;

    Boolean visited;
    int visitCount;

    public Cell()
    {
        rock = false;
        rock_number = -1;
        visited = false;
        visitCount = -1;
    }

    public int getVisitCount() {return visitCount;}

    public void setVisitCount(int visitCount) {this.visitCount = visitCount;}

    public Boolean getRock() {
        return rock;
    }

    public void setRock(Boolean rock) {
        this.rock = rock;
    }

    public int getRock_number() {
        return rock_number;
    }

    public void setRock_number(int rock_number) {
        this.rock_number = rock_number;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

}
