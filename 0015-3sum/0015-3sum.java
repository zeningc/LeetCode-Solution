class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++)   {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length - 1 && j < k; j++)   {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                while (k > j && nums[i] + nums[j] + nums[k] > 0)
                    k--;
                if (k > j && nums[i] + nums[j] + nums[k] == 0)
                    ans.add(List.of(nums[i], nums[j], nums[k]));
            }
        }
        
        return ans;
    }
}