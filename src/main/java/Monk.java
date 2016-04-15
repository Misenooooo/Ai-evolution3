import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.*;

/**
 * Created by michal on 14.4.2016.
 */
public class Monk {

    private int circuit;
    private int [] positions;
    private int rockQuantity;
    private int [] rockReactions;
    private int fitness;

    public Monk(){

    }

    public Monk(int circuit, int rockQuantity){
        this.circuit = circuit;
        this.rockQuantity = rockQuantity;
    }

    public void init(){
        positions = new int[circuit/2];
        rockReactions = new int[rockQuantity];

        List<Integer> list = new ArrayList<>();
        for (int i=0; i<circuit; i++) {
            list.add(new Integer(i));
        }
        int circuitHalf= circuit/2;
        Collections.shuffle(list);
        for (int i=0; i<circuitHalf; i++) {
            positions[i] = list.get(i);
        }
        Random random = new Random();
        for(int i = 0; i<rockQuantity;i++){
            if (random.nextFloat() > 0.5){
                rockReactions[i] = 1; // doprava alebo hore
            }
            else
            {
                rockReactions[i] = 0; // dolava alebo dole
            }
        }



    }

    public void reproductionHalfs(Monk foreignMonk) {
        int halfPos = positions.length / 2;
        for (int i = 0; i < halfPos; i++) {
            positions[i] = foreignMonk.getPositions()[i];
        }
        int halfRocks = rockReactions.length / 2;
        for (int i = 0; i < halfRocks; i++) {

            rockReactions[i] = foreignMonk.getRockReactions()[i];
        }
    }

    public void reproductionCrissCross(Monk foreignMonk) {

        for (int i = 0; i < positions.length; i++) {
            if((i % 2) == 0) {
                positions[i] = foreignMonk.getPositions()[i];
            }
        }
        for (int i = 0; i < rockReactions.length; i++) {
            if((i % 2) == 0){
                rockReactions[i] = foreignMonk.getRockReactions()[i];
            }
        }
    }


    public void mutate(){
        Random random = new Random();
        if (random.nextDouble() > 0.5){
            int pos = random.nextInt(positions.length);
            positions[pos] = random.nextInt(circuit);
        }else{
            int pos = random.nextInt(rockReactions.length);
            if(rockReactions[pos] == 1){
                rockReactions[pos] = 0;
            }else{
                rockReactions[pos] = 1;
            }
        }

    }




    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getCircuit() {
        return circuit;
    }

    public void setCircuit(int circuit) {
        this.circuit = circuit;
    }

    public int[] getPositions() {
        return positions;
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }

    public int getRockQuantity() {
        return rockQuantity;
    }

    public void setRockQuantity(int rockQuantity) {
        this.rockQuantity = rockQuantity;
    }

    public int[] getRockReactions() {
        return rockReactions;
    }

    public void setRockReactions(int[] rockReactions) {
        this.rockReactions = rockReactions;
    }


}
