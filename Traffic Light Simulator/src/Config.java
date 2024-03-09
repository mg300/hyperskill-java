public class Config {
    private int intervals;
    private int roads;
    private static Config config;
    private Config(){}
    public static Config getInstance(){
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public void setIntervals(int intervals) {
        this.intervals = intervals;
    }

    public void setRoads(int roads) {
        this.roads = roads;
    }

    public int getIntervals() {
        return intervals;
    }

    public int getRoads() {
        return roads;
    }
}
