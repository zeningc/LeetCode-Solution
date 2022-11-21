class Solution {
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++)   {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j])
                    continue;
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] == nums[k] || nums[j] == nums[k])
                        continue;
                    ans++;
                }
            }
        }
        
        return ans;
    }
}