import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by michal on 15.4.2016.
 */
public class SelectiveBreeding {
    private double mutationProbability;

    public List<Monk> selectiveBreeding(List<Monk> monks, int mode){
        List<Monk> newMonks= new ArrayList<>();

        if (mode == 1)
        {
                newMonks = Tournament2(monks);
                mutationProbability = 0.14;
        }
        else
        {
            mutationProbability = 0.01;
        }
        mutate(newMonks);


        return newMonks;
    }

    private List<Monk> Tournament2(List<Monk> monks) {
        int i = 0;

        List<Monk> newMonks = new ArrayList<>();
        Kryo copier = new Kryo();

        Random random = new Random();
        int rnd1;
        int rnd2;

        Monk first;
        Monk second;

        Monk parent1 =  monks.get(random.nextInt(monks.size()));
        Monk parent2;
        Monk child;

        int noReproductions = (monks.size()/4)*3;
        for(i = 0; i < noReproductions; i++)
        {
            rnd1 = random.nextInt(monks.size());
            rnd2 = random.nextInt(monks.size());

       //     System.out.println(rnd1+"  "+rnd2);

            first = monks.get(rnd1);
            second = monks.get(rnd2);

            parent1 = getBest(first,second);

            rnd1 = random.nextInt(monks.size());
            rnd2 = random.nextInt(monks.size());

      //      System.out.println(rnd1+"  "+rnd2 + " " + monks.size());

            first = monks.get(rnd1);
            second = monks.get(rnd2);

            parent2 = getBest(first,second);

            child = copier.copy(parent1);
            parent1.reproduction(parent2);

            newMonks.add(child);
        }
        for ( ; i  < monks.size();i++ )
        {
            child = copier.copy(parent1);
            child.init();
            newMonks.add(child);
        }


        return newMonks;
    }

    private Monk getBest(Monk first, Monk second) {
        Kryo copier = new Kryo();
        if (first.getFitness() > second.getFitness())
        {
            return copier.copy(first);
        }
        else
        {
            return copier.copy(second);
        }
    }

    private void mutate(List<Monk> newMonks) {
        Random random = new Random();
        for (Monk currMonk : newMonks) {
            if(random.nextDouble() < mutationProbability)
            {
              currMonk.mutate();
            }
        }
    }

}
