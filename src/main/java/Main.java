import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 13.4.2016.
 */
public class Main {
    // config
        public static final int mapLength = 5;
        public static final int mapWidth = 6;
        public static final int rockQuantity = 6;
        public static final int pocetPanakov = 1;
    //
    public static void main(String[] args) {
        Map map = new Map(mapLength,mapWidth,rockQuantity);
        map.init();

        FitnessCalculator calculator = new FitnessCalculator();

        List<Monk> monks = new ArrayList<>(pocetPanakov);
        for(int i = 0; i < pocetPanakov; i++)
        {
            Monk newMonk = new Monk((mapLength*2+mapWidth*2),rockQuantity);
            newMonk.init();
            monks.add(newMonk);
        }


        for (Monk currMonk : monks) {

            calculator.Calculate(currMonk,map);
        }

        }


}
