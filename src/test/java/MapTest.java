/**
 * Created by michal on 13.4.2016.
 */
 import org.junit.*;

 import java.util.ArrayList;
 import java.util.List;

public class MapTest {
    @Test
    public void TestMap(){
        Map map = new Map(20,20,6);
     //   map.init();
        int count = 0;
        int countRocks = 0;
   //     for (int i = 0; i < map.getLength(); i++)
   //     {
    //        for(int j = 0; j < map.getWindth(); j++)
    //        {
         //       if(map.getMap()[i][j].getRock() == true)
        //        {
                    countRocks++;
        //        }
                count++;
      //      }
    //    }
        List list = new ArrayList();
        Assert.assertEquals(5,countRocks);
        Assert.assertEquals(40,count);
    }
}
