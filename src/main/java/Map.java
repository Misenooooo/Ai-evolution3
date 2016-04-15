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
    }

    public void init(){
        Map = new Cell[length][windth];
        int count = 0;
        Random generator = new Random();
        for(int i = 0; i < length; i++)
        {
            for (int j = 0; j < windth; j++)
            {
                   Map[i][j] = new Cell();
                   if(count < rockQuantity && generator.nextFloat() > 0.8)
                   {
                       Map[i][j].setRock(true);
                       Map[i][j].setRock_number(count);
                       count++;
                   }
                    else
                   {
                       Map[i][j].setVisited(false);
                   }
            }

        }
        if(count == rockQuantity )
        {
            return;
        }
        this.init(); // donekonecna generujem mapu kym nevygenerujem spravnu
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
                    str.append("V ");
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

}
