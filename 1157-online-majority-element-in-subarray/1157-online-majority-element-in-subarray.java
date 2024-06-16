class MajorityChecker {
    Map<Integer, List<Integer>> m;
    List<Integer> rank;
    public MajorityChecker(int[] arr) {
        m = new HashMap<>();
        rank = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
            m.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        for (int a : m.keySet())
            rank.add(a);
        Collections.sort(rank, (a, b) -> m.get(b).size() - m.get(a).size());
    }
    
    public int query(int left, int right, int threshold) {
        int cnt = right - left + 1;
        for (int i = 0; i < rank.size() && cnt >= threshold; i++)   {
            int elem = rank.get(i);
            int leftPos = findLeft(m.get(elem), left);
            int rightPos = findRight(m.get(elem), right);
            if (rightPos - leftPos + 1 >= threshold)
                return elem;
            cnt -= rightPos - leftPos + 1;
        }
        
        return -1;
    }
    
    int findLeft(List<Integer> list, int pos)   {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) >= pos)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    int findRight(List<Integer> list, int pos)   {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) > pos)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return hi;
    }
    
    
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */