class Solution {
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] freq = new int[n];
        int distinctCnt = 0;
        for (int i = 0; i < n; i++) {
            freq[nums[i]]++;
            
            if (freq[nums[i]] == 1)
                distinctCnt++;
            else if (freq[nums[i]] == 2)
                distinctCnt--;
            
            dp[i] = i + 1 - distinctCnt + k;
            
            int[] frontFreq = new int[n];
            int frontDistinctCnt = 0;
            int[] backFreq = freq.clone();
            int backDistinctCnt = distinctCnt;
            for (int j = 0; j < i; j++) {
                frontFreq[nums[j]]++;
                if (frontFreq[nums[j]] == 1)
                    frontDistinctCnt++;
                else if (frontFreq[nums[j]] == 2)
                    frontDistinctCnt--;
                
                backFreq[nums[j]]--;
                if (backFreq[nums[j]] == 1)
                    backDistinctCnt++;
                else if(backFreq[nums[j]] == 0)
                    backDistinctCnt--;
                
                dp[i] = Math.min(dp[i], dp[j] + i - j - backDistinctCnt + k);
            }
        }
        
        return dp[n - 1];
    }
}