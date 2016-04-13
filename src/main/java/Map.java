import java.util.Random;

/**
 * Created by michal on 13.4.2016.
 */
public class Map {
    private int length;
    private int windth;
    private int rockQuantity;
    private Cell[][] Map;

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
                   if(count < rockQuantity && generator.nextInt() > 0.8)
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
        if(count == rockQuantity - 1)
        {
            return;
        }
        this.init(); // donekonecna generujem mapu kym nevygenerujem spravnu
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
