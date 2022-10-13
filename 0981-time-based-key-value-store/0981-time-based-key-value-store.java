class TimeMap {

    Map<String, TreeMap<Integer, String>> m;
    public TimeMap() {
        m = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!m.containsKey(key))    {
            m.put(key, new TreeMap<>());
        }
        m.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!m.containsKey(key))
            return "";
        TreeMap<Integer, String> treeMap = m.get(key);
        Integer prev = treeMap.floorKey(timestamp);
        if (prev == null)
            return "";
        return treeMap.get(prev);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */