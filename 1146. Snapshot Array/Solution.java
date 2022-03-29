class SnapshotArray {
    TreeMap<Integer, Integer>[] treeMapArr;
    int currSnap;
    public SnapshotArray(int length) {
        treeMapArr = new TreeMap[length];
        currSnap = 0;
    }
    
    public void set(int index, int val) {
        if (treeMapArr[index] == null)
            treeMapArr[index] = new TreeMap<Integer, Integer>();
        treeMapArr[index].put(currSnap, val);
    }
    
    public int snap() {
        return currSnap++;
    }
    
    public int get(int index, int snap_id) {
        if (treeMapArr[index] == null)
            return 0;
        Integer k = treeMapArr[index].floorKey(snap_id);
        if (k == null)
            return 0;
        return treeMapArr[index].get(k);
    }
}
