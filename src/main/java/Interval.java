import com.esotericsoftware.kryo.Kryo;

/**
 * Created by michal on 15.4.2016.
 */
public class Interval {
    private int start;
    private int end;
    private Monk monk;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int start, int end, Monk monk) {
        this.start = start;
        this.end = end;
        this.monk = monk;
    }

    public Boolean contain(int x){
        if(x >= start && x <= end){
            return true;
        }else{
            return false;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Monk getMonk() {
        return monk;
    }

    public void setMonk(Monk monk) {
        this.monk = monk;
    }

    public Monk getDeepCopyMonk() {
        Kryo copier = new Kryo();
        return copier.copy(monk);
    }
}
