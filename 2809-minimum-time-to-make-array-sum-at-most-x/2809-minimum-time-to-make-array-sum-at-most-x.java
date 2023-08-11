class Solution {
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[] dp = new int[n + 1];
        int[] presum = new int[n + 1];
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++)
            idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums2.get(a) - nums2.get(b));
        for (int i = 1; i <= n; i++)
            presum[i] = presum[i - 1] + nums2.get(idx[i - 1]);
        int sum = 0;
        for (int num : nums1)
            sum += num;
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++)    {
            int k = idx[i - 1];
            for (int j = n; j >= 0; j--)    {
                int nextVal = Integer.MAX_VALUE / 2;
                nextVal = Math.min(nextVal, dp[j] + nums1.get(k) + j * nums2.get(k));
                if (j >= 1)
                    nextVal = Math.min(nextVal, dp[j - 1] + presum[i - 1]);
                dp[j] = nextVal;
            }
        }
        
        for(int i = 0; i <= n; i++)
            if (dp[i] <= x)
                return i;
        
        return -1;
    }
}