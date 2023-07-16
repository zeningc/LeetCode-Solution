class Solution {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j, n = nums.length;
        for (j = 0; j < n; ++j)
            if (nums[j] - nums[i] > k * 2)
                i++;
        return j - i;
    }
}