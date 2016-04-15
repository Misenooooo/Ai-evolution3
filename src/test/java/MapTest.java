/**
 * Created by michal on 13.4.2016.
 */
 import org.junit.*;

public class MapTest {
    @Test
    public void TestMap(){
        Map map = new Map(20,20,6);
        map.initRandomRocks();
        int count = 0;
        int countRocks = 0;
        for (int i = 0; i < map.getLength(); i++)
        {
            for(int j = 0; j < map.getWindth(); j++)
            {
                if(map.getMap()[i][j] != null )
                {
                     if (map.Map[i][j].getRock() == true)
                     {
                         countRocks++;
                     }
                 }
                count++;
            }
        }

        Assert.assertEquals(6,countRocks);
        Assert.assertEquals(400,count);
    }

    @Test
    public void TestMonk(){
        int circuit = ((20*2)+(20*2))/2;
        Monk monk = new Monk(circuit,6);
        monk.init();

        for(int i =0; i < monk.getCircuit(); i++){

        }

        Assert.assertEquals(40,monk.getPositions().length);
        Assert.assertEquals(6,monk.getRockReactions().length);
    }
}
