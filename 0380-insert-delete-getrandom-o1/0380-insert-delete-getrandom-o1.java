class RandomizedSet {
    Map<Integer, Integer> valToIdxMap;
    Random random;
    List<Integer> valList;
    public RandomizedSet() {
        valToIdxMap = new HashMap<>();
        random = new Random();
        valList = new ArrayList<>(200001);
    }
    
    public boolean insert(int val) {
        if (valToIdxMap.containsKey(val))
            return false;
        valToIdxMap.put(val, valList.size());
        valList.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!valToIdxMap.containsKey(val))
            return false;
        int idxToRemove = valToIdxMap.get(val);
        if (idxToRemove != valList.size() - 1)  {
            int lastElem = valList.get(valList.size() - 1);
            valList.set(idxToRemove, lastElem);
            valToIdxMap.put(lastElem, idxToRemove);
        }
        valToIdxMap.remove(val);
        valList.remove(valList.size() - 1);
        
        return true;
    }
    
    public int getRandom() {
        return valList.get(random.nextInt(valList.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */