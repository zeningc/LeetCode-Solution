class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int hi = n - 1;
                for (int lo = j + 1; lo < n - 1 && lo < hi; lo++) {
                    if (lo > j + 1 && nums[lo] == nums[lo - 1])
                        continue;
                    while (lo < hi && (long)nums[lo] + nums[hi] + nums[i] + nums[j] > target)
                        hi--;
                    if (lo < hi && (long)nums[lo] + nums[hi] + nums[i] + nums[j] == target)
                        ans.add(List.of(nums[i], nums[j], nums[lo], nums[hi]));
                }
            }
        }
        
        return ans;
    }
}