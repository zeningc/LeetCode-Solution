class MajorityChecker {
    Map<Integer, List<Integer>> m;
    List<Integer> freqRank;
    public MajorityChecker(int[] arr) {
        m = new HashMap<>();
        freqRank = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)    {
            m.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }
        for (int key : m.keySet())    {
            freqRank.add(key);
        }
        
        Collections.sort(freqRank, (a, b) -> m.get(b).size() - m.get(a).size());
    }
    
    public int query(int left, int right, int threshold) {
        int cnt = right - left + 1;
        int idx = 0;
        int ans = -1;
        while (idx < freqRank.size() && cnt >= threshold)    {
            int candidate = freqRank.get(idx++);
            int leftPos = findLeft(m.get(candidate), left);
            int rightPos = findRight(m.get(candidate), right);
            int freq = rightPos - leftPos - 1;
            if (freq >= threshold)
                return candidate;
            cnt -= freq;
        }
        
        return ans;
    }
    
    int findLeft(List<Integer> arr, int insert) {
        int lo = 0;
        int hi = arr.size() - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) >= insert)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return hi;
    }
    
    int findRight(List<Integer> arr, int insert) {
        int lo = 0;
        int hi = arr.size() - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) > insert)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */