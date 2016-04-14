/**
 * Created by michal on 14.4.2016.
 */
public class FitnessCalculator {

     public enum Movement{
         UP,RIGHT,DOWN,LEFT
     }
    private int x; // sirka (do strany)
    private int y; // dlzka (hore dole)
    private Movement move;   // enum 4 pohyby

    public void Calculate(Monk monk, Map map) {
        for (int i = 0; i < monk.getPositions().length; i++) {
            getMove(monk,map,i);
            makeMove(map);
        }
    }

    private void makeMove(Map map) {
        int move_x = 0;
        int move_y = 0;
        switch (move){
            case UP: // TODO toto treba dorobit nejako inteligentne
                move_y = 1;
                break;
            case DOWN:
                move_y = -1;
                break;
            case LEFT:
                move_x = -1;
                break;
            case RIGHT:
                move_x = 1;
        }


        while((x >= 0 && x < map.getWindth()) && (y >= 0 && y < map.getLength()))
        {
            if(map.getMap()[y][x].getVisited() == true)
            {
                break;
            }
            map.getMap()[y][x].setVisited(true);
            x = x + move_x;
            y = y + move_y;
            System.out.println(move_x + " " + move_y);
        }
    }

    private void getMove(Monk monk, Map map, int i) {
        if (monk.getPositions()[i] < map.getWindth()) {
            x = monk.getPositions()[i];
            y = 0;
            move = Movement.DOWN;
        } else if (monk.getPositions()[i] < map.getLength() + map.getWindth()) {
            x = 0;
            y = monk.getPositions()[i] % map.getWindth();
            move = Movement.LEFT;
        } else if (monk.getPositions()[i] < map.getWindth() * 2 + map.getLength()) {
            x = monk.getPositions()[i] % (map.getLength() + map.getWindth());
            y = map.getLength() - 1;
            move = Movement.UP;
        } else {
            x = map.getWindth() - 1;
            y = monk.getPositions()[i] % (map.getLength() * 2 + map.getWindth());
            move = Movement.RIGHT;
        }
       System.out.println("AAAAAA Pozicie su y = " + y + " x je " + x + " zvislo je " + move + " Povodna pozicia je " + monk.getPositions()[i]);
    }
}