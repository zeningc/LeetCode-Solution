class UndergroundSystem {
    Map<Integer, Integer> idToCheckInTimeMap;
    Map<Integer, String> idToCheckInStationMap;
    Map<Interval, Integer> intervalSumMap;
    Map<Interval, Integer> intervalCntMap;
    public UndergroundSystem() {
        idToCheckInTimeMap = new HashMap<>();
        idToCheckInStationMap = new HashMap<>();
        intervalSumMap = new HashMap<>();
        intervalCntMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        idToCheckInTimeMap.put(id, t);
        idToCheckInStationMap.put(id, stationName);
    }
    
    public void checkOut(int id, String stationName, int t) {
        int time = t - idToCheckInTimeMap.get(id);
        Interval interval = new Interval(idToCheckInStationMap.get(id), stationName);
        intervalSumMap.put(interval, intervalSumMap.getOrDefault(interval, 0) + time);
        intervalCntMap.put(interval, intervalCntMap.getOrDefault(interval, 0) + 1);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        Interval interval = new Interval(startStation, endStation);
        return (double)intervalSumMap.get(interval) / intervalCntMap.get(interval);
    }
}

class Interval  {
    String startStation;
    String endStation;
    
    public Interval(String startStation, String endStation)   {
        this.startStation = startStation;
        this.endStation = endStation;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Interval))
            return false;
        Interval interval = (Interval)o;
        return interval.startStation.equals(startStation) && interval.endStation.equals(endStation);
    }
    
    @Override
    public int hashCode()   {
        int code = 0;
        if (startStation != null)
            code += startStation.hashCode();
        if (endStation != null)
            code += endStation.hashCode();
        
        return code;
    }
}


/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */

