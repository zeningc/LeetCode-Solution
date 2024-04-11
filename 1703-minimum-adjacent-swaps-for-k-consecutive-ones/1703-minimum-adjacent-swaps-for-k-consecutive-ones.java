class Solution {
    public int minMoves(int[] nums, int k) {
        if (k == 1)
            return 0;
        int cnt = 0;
        for (int num : nums)
            if (num == 1)
                cnt++;
        int[] ones = new int[cnt];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                continue;
            ones[j++] = i;
        }
        int l = 0;
        int preMid = 0;
        int frontSum = 0;
        int tailSum = 0;
        int ans = Integer.MAX_VALUE;
        for (int r = 0; r < cnt; r++) {
            if (r - l + 1 > k)  {
                frontSum -= ones[l++];
            }
            int mid = l + (r - l) / 2;
            if (r != mid)
                tailSum += ones[r];
            if (preMid != mid)    {
                frontSum += ones[preMid];
                tailSum -= ones[mid];
            }
            int frontCnt = mid - l;
            int tailCnt = r - mid;
            if (r - l + 1 == k) {
                int cur = frontCnt * ones[mid] - frontSum - frontCnt * (frontCnt + 1) / 2
                + tailSum - tailCnt * ones[mid] - tailCnt * (tailCnt + 1) / 2;
                ans = Math.min(ans, cur);
            }
            preMid = mid;
        }
        
        return ans;
    }
}