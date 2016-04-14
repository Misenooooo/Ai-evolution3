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


    public Monk(int circuit, int rockQuantity){
        this.circuit = circuit;
        this.rockQuantity = rockQuantity;
    }

    public void init(){
        positions = new int[circuit/2];
        rockReactions = new int[rockQuantity];

        ArrayList<Integer> list = new ArrayList<Integer>();
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
