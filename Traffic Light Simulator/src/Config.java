import java.util.LinkedList;
import java.util.Queue;

public class Config {
    private int intervals;
    private int roadsNum;
    private Queue<String> roads;
    private static Config config;
    private Config(){
        this.roads = new LinkedList<String>();
    }
    public static Config getInstance(){
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public void setRoadsNum(int roadsNum) {
        this.roadsNum = roadsNum;
    }

    public int getIntervals() {
        return intervals;
    }

    public int getRoadsNum() {
        return roadsNum;
    }
    public boolean addRoad(String road){
        if (roads.size()>=roadsNum) {
            return false;
        } else {
            roads.add(road);
            return true;
        }

    }
    public boolean deleteRoad(){
        if (roads.isEmpty()) {
            return false;
        } else {
            this.roads.remove();
            return true;
        }
    }
    public Queue<String> getRoads(){
        return this.roads;
    }
}
