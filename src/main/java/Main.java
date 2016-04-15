import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 13.4.2016.
 */
public class Main {
    // config
        public static final int mapLength = 20; // 5
        public static final int mapWidth = 20; // 6
        public static final int rockQuantity = 4; // 6
        public static final int pocetPanakov = 400; // 1 musi byt aspon 2, 3 aby to malo zmysel
        public static final int generationsLimit = 80;
        public static final int selectionMode = 1; // Mod selekcie zatial sa podporuje len cislo 1-Turnaj q=2;
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

        EvolutionAlgorithm algorithm = new EvolutionAlgorithm();

        Monk bestMonk = algorithm.Evolution(monks,map,generationsLimit,selectionMode);



    }

}
