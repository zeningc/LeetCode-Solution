class RandomizedCollection {
    Map<Integer, Set<Integer>> valToIdxsMap;
    Random random;
    List<Integer> list;
    public RandomizedCollection() {
        valToIdxsMap = new HashMap<>();
        random = new Random();
        list = new ArrayList<>(200000);
    }
    
    public boolean insert(int val) {
        if (!valToIdxsMap.containsKey(val)) {
            valToIdxsMap.put(val, new HashSet<>());
        }
        
        valToIdxsMap.get(val).add(list.size());
        list.add(val);
        
        return valToIdxsMap.get(val).size() == 1;
    }
    
    public boolean remove(int val) {
        if (!valToIdxsMap.containsKey(val)) {
            return false;
        }
        
        int idx = valToIdxsMap.get(val).iterator().next();
        int lastVal = list.get(list.size() - 1);
        list.set(idx, lastVal);
        valToIdxsMap.get(val).remove(idx);
        valToIdxsMap.get(lastVal).add(idx);
        valToIdxsMap.get(lastVal).remove(list.size() - 1);
        
        list.remove(list.size() - 1);
        if (valToIdxsMap.get(val).size() == 0)
            valToIdxsMap.remove(val);
        return true;
    }
    
    public int getRandom() {
        int randN = random.nextInt(list.size());
        return list.get(randN);
    }
}
