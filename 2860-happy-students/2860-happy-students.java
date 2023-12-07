class Solution {
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        Set<Integer> set = new HashSet<>(nums);
        int ans = 0;
        for (int selected = 0; selected <= n; selected++)   {
            if (set.contains(selected))
                continue;
            int lo = 0;
            int hi = n - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (nums.get(mid) >= selected)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            
            if (selected != hi + 1)
                continue;
            ans++;
        }
        
        return ans;
    }
}