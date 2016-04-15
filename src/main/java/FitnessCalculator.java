/**
 * Created by michal on 14.4.2016.
 */
public class FitnessCalculator {
    private int move_x = 0;
    private int move_y = 0;
     public enum Movement{
         UP,RIGHT,DOWN,LEFT
     }
    private int x; // sirka (do strany)
    private int y; // dlzka (hore dole)
    private Movement move;   // enum 4 pohyby

    public void Calculate(Monk monk, Map map) {
        for (int i = 0; i < monk.getPositions().length; i++) {
            getMove(monk,map,i);
            makeMove(monk,map);
            System.out.println(map.toString());
        }
    }

    private void makeMove(Monk monk, Map map) {
        switch (move){
            case UP: // TODO toto treba dorobit nejako inteligentne
                move_y = 1;
                move_x = 0;
                break;
            case DOWN:
                move_y = -1;
                move_x = 0;
                break;
            case LEFT:
                move_x = -1;
                move_y = 0;
                break;
            case RIGHT:
                move_x = 1;
                move_y = 0;
        }


        while((x >= 0 && x < map.getWindth()) && (y >= 0 && y < map.getLength()))
        {
            if(map.getMap()[y][x].getRock() == true || map.getMap()[y][x].getVisited() == true){
                if(check(x,y,map, monk) == false)
                {
                    break;
                }
            }

            map.getMap()[y][x].setVisited(true);
            x = x + move_x;
            y = y + move_y;
            System.out.println(move_y + " " + move_x +" " +y + " " + x);
        }
    }

    private Boolean check(int x, int y, Map map, Monk monk) {
        int temp_x = x;
        int temp_y = y;

        x = x - move_x;
        y = y - move_y;

        if((x < 0 || x >= map.getWindth()) || (y < 0 || y >= map.getLength())){
            return false;
        }
        if(map.getMap()[temp_y][temp_x].getRock() == true ){
          //  System.out.println(temp_x +" "+ temp_y+"  "+ x +" "+ y +"   "+ move_x + " " +move_y);
        //   System.out.println(map.getMap()[temp_y][temp_x].getRock()); // TODO toto fixnut
            int reaction =   monk.getRockReactions()[map.getMap()[temp_y][temp_x].getRock_number()];
        //    System.out.println(map.getMap()[temp_y][temp_x].getRock_number());
        }
        if(map.getMap()[y][x].getVisited() == true){

        }

        return false;
    }

    private void getMove(Monk monk, Map map, int i) {
        if (monk.getPositions()[i] < map.getWindth()) {
            x = monk.getPositions()[i];
            y = 0;
            move = Movement.UP;
        } else if (monk.getPositions()[i] < map.getLength() + map.getWindth()) {
            x = 0;
            y = monk.getPositions()[i] % map.getWindth();
            move = Movement.RIGHT;
        } else if (monk.getPositions()[i] < map.getWindth() * 2 + map.getLength()) {
            x = monk.getPositions()[i] % (map.getLength() + map.getWindth());
            y = map.getLength() - 1;
            move = Movement.DOWN;
        } else {
            x = map.getWindth() - 1;
            y = monk.getPositions()[i] % (map.getLength() * 2 + map.getWindth());
            move = Movement.LEFT;
        }
       System.out.println("AAAAAA Pozicie su y = " + y + " x je " + x + " pohyb je " + move + " Povodna pozicia je " + monk.getPositions()[i]);
    }
}