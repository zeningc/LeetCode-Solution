class MKAverage {
    TreeMap<Integer, Integer> maxMap;
    TreeMap<Integer, Integer> minMap;
    TreeMap<Integer, Integer> midMap;
    int m;
    int k;
    int minMapSize;
    int maxMapSize;
    int midMapSize;
    int midMapSum;
    Deque<Integer> q;
    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        q = new LinkedList<>();
        maxMap = new TreeMap<>();
        minMap = new TreeMap<>();
        midMap = new TreeMap<>();
        minMapSize = 0;
        maxMapSize = 0;
        midMapSize = 0;
        midMapSum = 0;
    }
    
    public void addElement(int num) {
        q.offer(num);
        
        if (minMapSize < k || num <= minMap.lastKey())   {
            update(minMap, num, 1);
            if (minMapSize > k) {
                shift(minMap, midMap, 1);
                if (maxMapSize < k)
                    shift(midMap, maxMap, 1);
            }
                
        }
        else if (!maxMap.isEmpty() && num >= maxMap.firstKey())  {
            update(maxMap, num, 1);
            if (maxMapSize > k) {
                shift(midMap, maxMap, -1);
            }
        }
        else    {
            update(midMap, num, 1);
            if (maxMapSize < k)
                shift(midMap, maxMap, 1);
        }
        
        
        if (q.size() <= m)
            return;
        
        int pop = q.poll();
        if (midMap.containsKey(pop))    {
            update(midMap, pop, -1);
        }
        else if (minMap.containsKey(pop))    {
            update(minMap, pop, -1);
            shift(minMap, midMap, -1);
        }
        else if (maxMap.containsKey(pop))   {
            update(maxMap, pop, -1);
            shift(midMap, maxMap, 1);
        }
        
    }
    
    public int calculateMKAverage() {
        if (q.size() < m)
            return -1;
        return midMapSum / (m - 2 * k);
    }
    
    private void update(TreeMap<Integer, Integer> m, int num, int delta) {
        int cnt = m.getOrDefault(num, 0) + delta;
        if (cnt == 0)
            m.remove(num);
        else
            m.put(num, cnt);
        
        if (m == maxMap)    {
            maxMapSize += delta;
        }
        else if (m == minMap)   {
            minMapSize += delta;
        }
        else if (m == midMap)   {
            midMapSize += delta;
            midMapSum += delta * num;
        }
    }
    
    private void shift(TreeMap<Integer, Integer> a, TreeMap<Integer, Integer> b, int delta) {
        if (delta == 1) {
            int num = a.lastKey();
            update(a, num, -1);
            update(b, num, 1);
            return;
        }
        int num = b.firstKey();
        update(a, num, 1);
        update(b, num, -1);
    }
}
/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */