import javax.management.RuntimeErrorException;

/**
 * Created by michal on 14.4.2016.
 */
public class FitnessCalculator {
    private int cellsVisited = 0;


    private int move_x = 0;
    private int move_y = 0;

     public enum Movement{
         UP,RIGHT,DOWN,LEFT
     }

    private int x; // sirka (do strany)
    private int y; // dlzka (hore dole)

    private Movement move;   // enum 4 pohyby

    public boolean Calculate(Monk monk, Map map) {
        cellsVisited = 0;
        move_x = 0;
        move_y = 0;
        x = 0;
        y = 0;
        move = null;
        for (int i = 0; i < monk.getPositions().length; i++) {
            getMove(monk,map,i);
            if(makeMove(monk,map) == false) // ak sa mnich dakde zasekol
            {
                break;
            }
     //       System.out.println(map.toString());
        }

        monk.setFitness(cellsVisited);

        if(monk.getFitness() == (map.getWindth()*map.getLength())-map.getRockQuantity())
        {
            return true;
        }
        return false;
    }

    private boolean makeMove(Monk monk, Map map) {
        switch (move){
            case UP:
                move_y = 1;
                move_x = 0;
                break;
            case DOWN:
                move_y = -1;
                move_x = 0;
                break;
            case LEFT:
                move_y = 0;
                move_x = -1;
                break;
            case RIGHT:
                move_y = 0;
                move_x = 1;
        }
        while((x >= 0 && x < map.getWindth()) && (y >= 0 && y < map.getLength()))
        {

            if(map.getMap()[y][x].getRock() == true || map.getMap()[y][x].getVisited() == true)
            {
                int status = check(map, monk);
                if(status == 1) // koniec a pokracuje sa na dalsi start
                {
                    break;
                }
                else if(status == -1)
                {
                    return false; // mnich sa dakde zasekol uprostred mapy
                }
            }

            if(map.getMap()[y][x].getVisited() == false)
            {
                map.getMap()[y][x].setVisited(true);
                cellsVisited++;
            }
            x = x + move_x;
            y = y + move_y;
       //     System.out.println(move_y + " " + move_x +" " +y + " " + x);
        }

        return true;
    }

    private int check(Map map, Monk monk) {
        int temp_x = x;
        int temp_y = y;

        x = x - move_x;
        y = y - move_y;

        if((x < 0 || x >= map.getWindth()) || (y < 0 || y >= map.getLength()))
        {
            return 1; // koniec a pokracuje sa na dalsi start
        }
        if(map.getMap()[temp_y][temp_x].getRock() == true )
        {
          //  System.out.println(temp_x +" "+ temp_y+"  "+ x +" "+ y +"   "+ move_x + " " +move_y);
        //   System.out.println(map.getMap()[temp_y][temp_x].getRock());
            int rock_number = map.getMap()[temp_y][temp_x].getRock_number();

            int reaction = monk.getRockReactions()[monk.getRockReactions()[rock_number]];

            if(move == Movement.DOWN || move == Movement.UP)
            {
                if(reaction == 1) // hore alebo vpravo
                {
                    move = Movement.RIGHT;
                    move_x = 1;
                    move_y = 0;
                //    System.out.println("Skusam ist vpravo(na mape vpravo ))"+reaction+" "+rock_number);
                    if(valid_position(map) == false)
                    {
                        move = Movement.LEFT;
                        move_x = -1;
                        move_y = 0;
              //          System.out.println("Skusam ist vlavo(na mape vlavo ))"+reaction+" "+rock_number);
                        if(valid_position(map) == false)
                        {
                //            System.out.println("Koniec hry zasekol som sa");
                            return -1; // Koniec hry
                        }
                    }
                }
                else
                {
                    move = Movement.LEFT;
                    move_x = -1;
                    move_y = 0;
             //       System.out.println("Skusam ist vlavo(na mape vlavo ))"+reaction+" "+rock_number);
                    if(valid_position(map) == false)
                    {
                        move = Movement.RIGHT;
                        move_x = 1;
                        move_y = 0;
              //          System.out.println("Skusam ist vpravo(na mape vpravo ))"+reaction+" "+rock_number);
                        if(valid_position(map) == false)
                        {
              //              System.out.println("Koniec hry zasekol som sa");
                            return -1;
                        }
                    }
                }
                return 0; // platna pozicia pokracuj vo vykonavani
            }
            else if(move == Movement.RIGHT || move == Movement.LEFT)
            {
                if(reaction == 1) // hore alebo vpravo
                {
                    move = Movement.UP;
                    move_x = 0;
                    move_y = 1;
             //       System.out.println("Skusam ist hore(na mape dole ))"+reaction+" "+rock_number);
                    if(valid_position(map) == false)
                    {
                        move = Movement.DOWN;
                        move_x = 0;
                        move_y = -1;
            //            System.out.println("Skusam ist dole(na mape hore ))"+reaction+" "+rock_number);
                        if(valid_position(map) == false)
                        {
             //               System.out.println("Koniec hry zasekol som sa");
                            return -1; // Koniec hry
                        }
                    }
                }
                else
                {
                    move = Movement.DOWN;
                    move_x = 0;
                    move_y = -1;
            //        System.out.println("Skusam ist dole(na mape hore ))"+reaction+" "+rock_number);
                    if(valid_position(map) == false)
                    {
               //         System.out.println("Skusam ist hore(na mape dole ))"+reaction+" "+rock_number);
                        move = Movement.UP;
                        move_x = 0;
                        move_y = 1;
                        if(valid_position(map) == false)
                        {
               //             System.out.println("Koniec hry zasekol som sa");
                            return -1; // Koniec hry
                        }
                    }
                }
                return 0;

            }
          //  System.out.println("Rock " + rock_number + "reaction" + reaction);
        //    System.out.println(map.getMap()[temp_y][temp_x].getRock_number());
        }
        if(map.getMap()[y][x].getVisited() == true){
            if(move == Movement.DOWN || move == Movement.UP)
            {
                move = Movement.RIGHT;
                move_x = 1;
                move_y = 0;
       //         System.out.println("Skusam ist vpravo(na mape vpravo ))");
                if(valid_position(map) == false)
                {
                    move = Movement.LEFT;
                    move_x = -1;
                    //System.out.print("s");
                    move_y = 0;
           ///         System.out.println("Skusam ist vlavo(na mape vlavo ))");
                    if(valid_position(map) == false)
                    {
          //              System.out.println("Koniec hry zasekol som sa");
                        return -1; // Koniec hry
                    }
                }
                return 0;
            }
            else if(move == Movement.LEFT || move == Movement.RIGHT)
            {
                move = Movement.UP;
                move_x = 0;
                move_y = 1;
         //       System.out.println("Skusam ist hore(na mape dole ))");
                if(valid_position(map) == false)
                {
                    move = Movement.DOWN;
                    move_x = 0;
                    move_y = -1;
          //          System.out.println("Skusam ist dole(na mape hore ))");
                    if(valid_position(map) == false)
                    {
           //             System.out.println("Koniec hry zasekol som sa");
                        return -1; // Koniec hry
                    }
                }
            }
            return 0;
        }

        return 404;
    }

    private boolean valid_position(Map map) {
        int temp_x = x + move_x;
        int temp_y = y + move_y;

        if((temp_x < 0 || temp_x >= map.getWindth()) || (temp_y < 0 || temp_y >= map.getLength())) // vyjdem z mapy
        {
            return true;
        }

        if(map.getMap()[temp_y][temp_x].getRock() == true || map.getMap()[temp_y][temp_x].getVisited() == true){
            return  false;
        }
        return true;
    }

    private void getMove(Monk monk, Map map, int i) {
        if (monk.getPositions()[i] < map.getWindth()) {
            x = monk.getPositions()[i];
            y = 0;
            move = Movement.UP;
        } else if (monk.getPositions()[i] < map.getLength() + map.getWindth()) {
            x = 0;
            y = monk.getPositions()[i] - map.getWindth();
            move = Movement.RIGHT;
        } else if (monk.getPositions()[i] < map.getWindth() * 2 + map.getLength()) {
            x = monk.getPositions()[i] - (map.getLength() + map.getWindth());
            y = map.getLength() - 1;
            move = Movement.DOWN;
        } else {
            x = map.getWindth() - 1;
            y = monk.getPositions()[i]  - (map.getLength()  + map.getWindth()*2);
            move = Movement.LEFT;
        }
    //   System.out.println("AAAAAA Pozicie su y = " + y + " x je " + x + " pohyb je " + move + " Povodna pozicia je " + monk.getPositions()[i]);
    }

}