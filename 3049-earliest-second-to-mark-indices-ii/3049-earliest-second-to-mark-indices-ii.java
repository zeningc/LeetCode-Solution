class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        for (int i = 0; i < changeIndices.length; i++)
            changeIndices[i]--;
        
        int lo = 1;
        int hi = changeIndices.length;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(nums, changeIndices, mid))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo == changeIndices.length + 1 ? -1 : lo;
    }
    
    boolean check(int[] nums, int[] changeIndices, int s) {
        int n = nums.length;
        int[] first = new int[n];
        Arrays.fill(first, -1);
        for (int i = 0; i < s; i++)
            if (first[changeIndices[i]] == -1 && nums[changeIndices[i]] != 0)
                first[changeIndices[i]] = i;
        TreeSet<int[]> ts = new TreeSet<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        for (int t = s - 1; t >= 0; t--)    {
            int cur = changeIndices[t];
            if (first[cur] != t)
                continue;
            
            ts.add(new int[] {cur, nums[cur]});
            if (s - t - ts.size() < ts.size())  
                ts.remove(ts.first());
        }
        return ts.stream().mapToLong(a->(int)a[1]).sum() + s - n - ts.size() >= Arrays.stream(nums).mapToLong(i -> (long)i).sum();
    }
}