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
    public void addRoad(String road){
        this.roads.add(road);
    }
    public void deleteRoad(){
        this.roads.remove();
    }
    public Queue<String> getRoads(){
        return this.roads;
    }
}
