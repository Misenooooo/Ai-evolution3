/**
 * Created by michal on 13.4.2016.
 */


public class Cell {
    Boolean rock;
    int rock_number;
    Boolean visited;

    public Cell()
    {
        rock = false;
        rock_number = -1;
        visited = false;
    }

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
