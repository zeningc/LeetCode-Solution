class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        long[] cnt = new long[n];
        long ans = 0;
        for (int j = 0; j < n; j++) {
            int lowerCnt = 0;
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j])  {
                    lowerCnt++;
                    ans += cnt[i];
                }
                else    {
                    cnt[i] += lowerCnt;
                }
            }
        }
        return ans;
    }
}