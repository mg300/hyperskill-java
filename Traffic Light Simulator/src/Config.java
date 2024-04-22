import java.util.LinkedList;
import java.util.Queue;

public class Config {
    private int intervals;
    private int roadsNum;
    private Queue<String> roads;

    public int[] times;
    public int openIndex;

    private static Config config;
    private Config(){
        this.roads = new LinkedList<>();
        this.openIndex=0;

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
            times = new int[roads.size()];
            initTimes();
            return true;
        }

    }
    public boolean deleteRoad(){
        if (roads.isEmpty()) {
            return false;
        } else {
            this.roads.remove();
            times = new int[roads.size()];
            initTimes();
            return true;
        }
    }
    public Queue<String> getRoads(){
        return this.roads;
    }
    private void initTimes(){
        for(int i=0; i<roads.size(); i++){
            if(i==0) times[i] = intervals;
            else{

            times[i] = i*intervals;
            }
        }
    }
}
