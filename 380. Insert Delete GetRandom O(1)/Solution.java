class RandomizedSet {
    List<Integer> list;
    Map<Integer, Integer> valToIdxMap;
    Random random;
    public RandomizedSet() {
        list = new ArrayList<>(200001);
        valToIdxMap = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (valToIdxMap.containsKey(val))
            return false;
        valToIdxMap.put(val, list.size());
        list.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!valToIdxMap.containsKey(val))
            return false;
        int idx = valToIdxMap.get(val);
        int lastElement = list.get(list.size() - 1);
        list.set(idx, lastElement);
        list.remove(list.size() - 1);
        valToIdxMap.put(lastElement, idx);
        valToIdxMap.remove(val);
        return true;
    }
    
    public int getRandom() {
        int randInt = random.nextInt(list.size());
        return list.get(randInt);
    }
}
