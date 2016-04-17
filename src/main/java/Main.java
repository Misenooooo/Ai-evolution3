import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * Created by Michal Dolnak on 13.4.2016.
 */

public class Main {

    /**
     * @ Michal Dolnak
     *
     */
    public static void main(String[] args) {
        // config
        int mapLength = 10; // 5
        int mapWidth = 12; // 6
        int rockQuantity = 6; // 6 musi byt aspon 1
        int numberOfMonks = 20; // 1 musi byt aspon 2, 3 aby to malo zmysel
        int generationsLimit = 100;
        int selectionMode = 2; // Mod selekcie podporuje cislo 1-Turnaj q=2; cislo - 2 Ruleta
        //

        Map map = null;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Run standard test yes/no?");
        String input = scanner.nextLine();

        if(input.equalsIgnoreCase("yes") == true) {

            map = new Map(mapLength,mapWidth,rockQuantity);
            map.initTest1();
        }else{
            System.out.println("Set Length of map(at least 2)");
            mapLength = scanner.nextInt();
            System.out.println("Set Width of map(at least 2)");
            mapWidth = scanner.nextInt();
            System.out.println("Set number of rocks on the map(at least 2)");
            rockQuantity = scanner.nextInt();
            System.out.println("Set number of Monks in one generation(at least 2)");
            numberOfMonks = scanner.nextInt();
            System.out.println("Set maximum number of generations");
            generationsLimit = scanner.nextInt();
            System.out.println("Set mode of selection(1 or 2)");
            selectionMode = scanner.nextInt();
            map = new Map(mapLength,mapWidth,rockQuantity);
            map.init();
            map.initRandomRocks();
        }

        List<Monk> monks = new ArrayList<>(numberOfMonks);

        for(int i = 0; i < numberOfMonks; i++)
        {
            Monk newMonk = new Monk((mapLength*2+mapWidth*2),rockQuantity);
            newMonk.init();
            monks.add(newMonk);
        }

        EvolutionAlgorithm algorithm = new EvolutionAlgorithm();

        Monk bestMonk = algorithm.Evolution(monks,map,generationsLimit,selectionMode);

    }

}
