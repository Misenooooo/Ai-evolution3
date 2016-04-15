import java.util.Random;

/**
 * Created by michal on 13.4.2016
 *
 * Example:
 * Length = 5
 * Width = 6
 *y\x 0  1  2  3  4  5
 * 6  R  N  N  N  R  N 17
   7  N  R  R  N  N  R 18
   8  N  N  R  N  N  N 19
   9  N  N  N  N  N  N 20
   10 N  N  N  N  N  N 21
     11 12 13 14 15 16
  Pozicie su y = 2 x je 0 pohyb je RIGHT Povodna pozicia je 8
 *0 1 2 1 // pohyb po y, pohyb po x, pozicia y, pozicia x
  0 1 2 2
  R N N N R N
  N R R N N R
  V V R N N N
  N N N N N N
  N N N N N N  // vysledok este bez prechadzania
 *
 */

public class Map {
    private int length;
    private int windth;
    private int rockQuantity;
    public Cell[][] Map;

    public Map(){

    }

    public Map(int length, int windth, int rockQuantity){
        this.length = length;
        this.windth = windth;
        this.rockQuantity = rockQuantity;
        this.Map = new Cell[length][windth];
    }

    public void init(){
        for(int i = 0; i < length; i++)
        {
            for (int j = 0; j < windth; j++)
            {
                Map[i][j] = new Cell(); // toto je nutne
            }

        }
    }

    public void initRandomRocks(){

    }
    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            for(int j = 0; j < windth; j++)
            {
                if(getMap()[i][j].getRock() == false && getMap()[i][j].getVisited() == false){
                    str.append("N ");
                }
                if(getMap()[i][j].getRock() == true){
                    str.append("R ");
                }
                if(getMap()[i][j].getVisited() == true){
                    str.append(getMap()[i][j].visitCount+" ");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

    public Cell[][] getMap() {
        return Map;
    }

    public void setMap(Cell[][] map) {
        Map = map;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWindth() {
        return windth;
    }

    public void setWindth(int windth) {
        this.windth = windth;
    }

    public int getRockQuantity() {
        return rockQuantity;
    }

    public void setRockQuantity(int rockQuantity) {
        this.rockQuantity = rockQuantity;
    }

    public void initTest1() {
        init();
        Map[1][5].setRock(true); Map[1][5].setRock_number(0);
        Map[2][1].setRock(true); Map[2][1].setRock_number(1);
        Map[3][4].setRock(true); Map[3][4].setRock_number(2);
        Map[4][2].setRock(true); Map[4][2].setRock_number(3);
        Map[6][8].setRock(true); Map[6][8].setRock_number(4);
        Map[6][9].setRock(true); Map[6][9].setRock_number(5);


    }
}
