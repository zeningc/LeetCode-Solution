class MyCalendarThree {
    TreeMap<Integer, Integer> treeMap;
    public MyCalendarThree() {
        treeMap = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        treeMap.put(start, treeMap.getOrDefault(start, 0) + 1);
        treeMap.put(end, treeMap.getOrDefault(end, 0) - 1);
        int ans = 0;
        int temp = 0;
        for (int key : treeMap.keySet())    {
            temp += treeMap.get(key);
            ans = Math.max(ans, temp);
        }
        
        return ans;
    }
}
