import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michal on 13.4.2016.
 */
public class Main {
    // config
        public static final int mapLength = 10; // 5
        public static final int mapWidth = 12; // 6
        public static final int rockQuantity = 6; // 6 musi byt aspon 1
        public static final int pocetPanakov = 20; // 1 musi byt aspon 2, 3 aby to malo zmysel
        public static final int generationsLimit = 20;
        public static final int selectionMode = 1; // Mod selekcie zatial sa podporuje len cislo 1-Turnaj q=2;
    //
    public static void main(String[] args) {
        Map map = new Map(mapLength,mapWidth,rockQuantity);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Run Random test yes/no?");
        String input = scanner.nextLine();

        if(input.equalsIgnoreCase("yes") == true) {
            map.initRandomRocks();
        }else{
            if(mapLength == 10 && mapWidth == 12 && rockQuantity == 6) {
                map.initTest1();
            }else{
                System.out.println("Zle nastavene parametre testu, musia byt mapLength == 10 && mapWidth == 12 && rockQuantity == 6");
                return;
            }

        }

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
