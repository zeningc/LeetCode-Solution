class DetectSquares {
    Map<Pair<Integer, Integer>, Integer> map;
    public DetectSquares() {
        map = new HashMap<>();
    }
    
    public void add(int[] point) {
        Pair<Integer, Integer> p = new Pair<>(point[0], point[1]);
        map.put(p, map.getOrDefault(p, 0) + 1);
    }
    
    public int count(int[] point) {
        int cnt = 0;
        Pair<Integer, Integer> p = new Pair<>(point[0], point[1]);
        for (Pair<Integer, Integer> b : map.keySet())   {
            if (p.getKey() == b.getKey() && p.getValue() == b.getValue() || Math.abs(p.getKey() - b.getKey()) != Math.abs(p.getValue() - b.getValue()))
                continue;
            Pair<Integer, Integer> c = new Pair<>(p.getKey(), b.getValue());
            Pair<Integer, Integer> d = new Pair<>(b.getKey(), p.getValue());
            cnt += map.get(b) * map.getOrDefault(c, 0) * map.getOrDefault(d, 0);
        }
        
        return cnt;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */