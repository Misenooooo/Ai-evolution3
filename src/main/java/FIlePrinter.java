import java.io.*;
import java.util.List;

import static java.nio.charset.StandardCharsets.*;

/**
 * Created by Michal Dolnak on 16.4.2016.
 * Class is used to print output to Vystup.txt
 */
public class FIlePrinter {
    private static Writer writer = null;
    private static int counter;

    public  void initPrintWriter() {
        try {
            counter = 0;
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Vystup.txt"), UTF_8)); // vytvorenie instancie Writeru
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void closePrintWriter() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printGenererations(int generations){
        try {
            writer.write(new String("Generacia: " + generations+"\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printIndividuals(Map map, int individual){
        try {
            writer.write(new String("Jedinec cislo: "+ individual + "\n"));
            writer.write(map.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFitness(List<Monk> monks){
        try{
            writer.write("Fitnes jednotlivých mníchov:\n");
            int count = 1;
            for (Monk currMonk : monks){
                writer.write(new String(count++ +". "+currMonk.getFitness())+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printOverallScore(double overallScore, double avgScore){
        try {
            writer.write("Celkové fitness mníchov: "+overallScore +". priemerné fitness: " +avgScore+ "\n");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void printSolution(Map map, Monk monk, int monkNumber){
        try {
            writer.write("Mních, ktoré prehrabe celu záhradku je: "+monkNumber+ " mních v generácii \n");
            writer.write("Reakcie na kamene(cislo kamena/reakcia): ");
            for (int i = 0; i < monk.getRockReactions().length; i++){
                writer.write(new String(i + " : "+monk.rockReactionsToString(i))+" ");
            }
            writer.write("\n");
            writer.write(map.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printTestParams(int selectionMode, Map map){
        try {
            if(selectionMode == 1){
                writer.write(new String("Selekcia turnajom: "+"\n"));
            }else if (selectionMode == 2){
                writer.write(new String("Selekcia ruletou: "+"\n"));
            }
            writer.write("Mapa je nasledujuca: \n");
            writer.write(map.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}




