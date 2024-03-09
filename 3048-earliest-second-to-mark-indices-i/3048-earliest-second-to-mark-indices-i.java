class Solution {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int[][] presum = new int[changeIndices.length + 1][nums.length];
        for (int i = 0; i < changeIndices.length; i++)  {
            changeIndices[i] = changeIndices[i] - 1;
            for (int j = 0; j < nums.length; j++)   {
                presum[i + 1][j] = presum[i][j] + (changeIndices[i] == j ? 1 : 0);
            }
        }
        int lo = 0;
        int hi = changeIndices.length;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(presum, nums, changeIndices, mid))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo == changeIndices.length + 1 ? -1 : lo;
    }
    
    boolean check(int[][] presum, int[] nums, int[] changeIndices, int s)   {
        int gap = 0;
        int cnt = 0;
        boolean[] marked = new boolean[nums.length];
        for (int i = 0; i < s; i++)    {
            int cur = changeIndices[i];
            if (marked[cur] || presum[s][cur] - presum[i][cur] != 1)    {
                gap++;
                continue;
            }
            if (gap >= nums[cur]) {
                gap -= nums[cur];
                marked[cur] = true;
                cnt++;
                continue;
            }
            return false;
        }
        return cnt == nums.length;
    }
}