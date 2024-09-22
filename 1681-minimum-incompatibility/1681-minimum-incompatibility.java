class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)    {
            int cnt = freq.getOrDefault(num, 0) + 1;
            if (cnt > k)
                return -1;
            freq.put(num, cnt);
        }
        
        
        int state = (1 << (n / k)) - 1;
        Map<Integer, Integer> stateToVal = new HashMap<>();
        // Gospers Hack
        while (state < (1 << n))    {
            if (!containsDuplicate(nums, state, k))  {
                stateToVal.put(state, computeVal(nums, state, k));
            }
            
            int c = state & -state;
            int r = state + c;
            state = (((state ^ r) >> 2) / c) | r;
        }
        List<Integer> dpState = new ArrayList<>();
        for (int totalState = (1 << n) - 1; totalState >= 0; totalState--)
            if (countBit(totalState) % (n / k) == 0)
                dpState.add(totalState);
        
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (Map.Entry<Integer, Integer> entry : stateToVal.entrySet()) {
            int cur = entry.getKey();
            int val = entry.getValue();
            for (int totalState : dpState)  {
                if ((totalState & cur) != cur)
                    continue;
                dp[totalState] = Math.min(dp[totalState], dp[totalState - cur] + val);
            }
        }
        
        return dp[(1 << n) - 1];
    }
    
    int countBit(int state) {
        int cnt = 0;
        while (state != 0)  {
            cnt++;
            state &= (state - 1);
        }
        return cnt;
    }
    
    boolean containsDuplicate(int[] nums, int state, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            if ((state & (1 << i)) != 0)
                if (set.contains(nums[i]))
                    return true;
                else
                    set.add(nums[i]);
        
        return false;
    }
    
    int computeVal(int[] nums, int state, int k)  {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++)
            if ((state & (1 << i)) != 0)  {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }
        
        return max - min;
    }
}