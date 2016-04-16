import com.esotericsoftware.kryo.Kryo;

import java.util.List;

/**
 * Created by michal on 15.4.2016.
 */
public class EvolutionAlgorithm {

    public Monk Evolution(List<Monk> monks,Map map, int generations,int selectionMode){

        Kryo copier = new Kryo();
        Map mapCopy;

        FitnessCalculator calculator = new FitnessCalculator();
        SelectiveBreeding breeding = new SelectiveBreeding();
        FIlePrinter fIlePrinter = new FIlePrinter();
        fIlePrinter.initPrintWriter();

        Monk bestMonk = null;



        for(int i = 0; i < generations; i++)
        {
            fIlePrinter.printGenererations(i);
            int count = 0;
            for (Monk currMonk : monks)
            {
                mapCopy = copier.copy(map);

           //   System.out.println("Kopia:\n" + mapCopy.toString());
                if(calculator.Calculate(currMonk, mapCopy) == true) // podarilo sa dakomu prehrabat zahradu
                {
                    System.out.println("Best Monk ");
                    fIlePrinter.printSolution(mapCopy,currMonk, count);
                    fIlePrinter.closePrintWriter();
                    return currMonk;
                }
                count++;
           //     fIlePrinter.printIndividuals(mapCopy,count++);
            }

            fIlePrinter.printFitness(monks);

           // System.out.println("\n\n\n\n\n\n\n\nGeneration: " +i);
            float OverallFitness = 0;
            for (Monk currMonk : monks)
            {
                OverallFitness = OverallFitness + currMonk.getFitness();
             //   System.out.println("Fitness : " + currMonk.getFitness());

            }
           // System.out.println("Overall fitness " + OverallFitness + " avg fitness " + OverallFitness/monks.size() );
            fIlePrinter.printOverallScore(OverallFitness,OverallFitness/monks.size());


            monks = breeding.selectiveBreeding(monks,selectionMode);



        }
        fIlePrinter.closePrintWriter();
        return null;
    }

}
