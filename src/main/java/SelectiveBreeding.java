import com.esotericsoftware.kryo.Kryo;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by michal on 15.4.2016.
 */
public class SelectiveBreeding {
    private double mutationProbability;
    private int noReproductions;

    public List<Monk> selectiveBreeding(List<Monk> monks, int mode){
        List<Monk> newMonks= new ArrayList<>();
        if (mode == 1)
        {
                mutationProbability = 0.10;
                newMonks = Tournament2(monks);

        }
        else if (mode == 2)
        {
            mutationProbability = 0.03;
            newMonks = roulette(monks);

        }
        mutate(newMonks);


        return newMonks;
    }

    private List<Monk> Tournament2(List<Monk> monks) {
        int i = 0;
        double ChanceOfWin = 1;

        noReproductions = (monks.size()/4)*3;

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


        for(i = 0; i < noReproductions; i++)
        {
            rnd1 = random.nextInt(monks.size());
            rnd2 = random.nextInt(monks.size());

       //     System.out.println(rnd1+"  "+rnd2);

            first = monks.get(rnd1);
            second = monks.get(rnd2);

            if(random.nextDouble() < ChanceOfWin) {
                parent1 = getBest(first, second);
            }else{
                parent1 = getWorst(first,second);
            }
            rnd1 = random.nextInt(monks.size());
            rnd2 = random.nextInt(monks.size());

      //      System.out.println(rnd1+"  "+rnd2 + " " + monks.size());

            first = monks.get(rnd1);
            second = monks.get(rnd2);

            if(random.nextDouble() < ChanceOfWin) {
                parent2 = getBest(first, second);
            }else{
                parent2 = getWorst(first,second);
            }


            child = copier.copy(parent1);
            if (random.nextDouble() > 0.5) {
                parent1.reproductionHalfs(parent2);
            }else{
                parent1.reproductionCrissCross(parent2);
            }
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

     List<Monk> roulette(List<Monk> monks) { // TODO  potom napisat nejaku dokumentaciu do kodu, normalnu pdf dokumentaciu a este umoznit zadat vstup
         List<Monk> newMonks = new ArrayList<>();
         Random random = new Random();
         Kryo copier = new Kryo();

         Monk parent1 = monks.get(random.nextInt(monks.size()));;
         Monk parent2 = null;

         int i = 0;

         List<Interval> intervals = new ArrayList<>();
         int sum = 0;

         for (Monk currMonk: monks) {
            Interval interval = new Interval(sum,sum+currMonk.getFitness(),currMonk);
            intervals.add(interval);
            sum = sum + currMonk.getFitness() + 1;
         }
         System.out.println("Sum is "+ sum);
         int randomNumber;

         noReproductions = monks.size()-1;
         for (i = 0; i < noReproductions;i++)
         {
             randomNumber = random.nextInt(sum);
             for (Interval currInterval : intervals) {
                 if(currInterval.contain(randomNumber) == true){
                     parent1 = currInterval.getDeepCopyMonk();
                     break;
                 }
             }
             randomNumber = random.nextInt(sum);
             for (Interval currInterval : intervals) {
                 if(currInterval.contain(randomNumber) == true){
                     parent2 = currInterval.getDeepCopyMonk();
                     break;
                 }
             }

             if(random.nextDouble() > 0.5){
                 parent1.reproductionHalfs(parent2);
             }else{
                 parent1.reproductionCrissCross(parent2);
             }
             newMonks.add(parent1);

         }

         for ( ; i  < monks.size();i++ )
         {
             parent1 = copier.copy(parent1);
             parent1.init();
             newMonks.add(parent1);
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

    private Monk getWorst(Monk first, Monk second) {
        Kryo copier = new Kryo();
        if (first.getFitness() < second.getFitness())
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
