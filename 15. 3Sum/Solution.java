class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int j = i + 1;
            int k = n - 1;
            while (j < k)   {
                if (nums[i] + nums[j] + nums[k] == 0)   
                    ans.add(List.of(nums[i], nums[j], nums[k]));
                
                if (nums[i] + nums[j] + nums[k] <=0)   {
                    j++;
                    while (j < k && nums[j - 1] == nums[j])
                        j++;
                }
                else if (nums[i] + nums[j] + nums[k] > 0)   {
                    k--;
                    while (j < k && nums[k + 1] == nums[k])
                        k--;
                }
            }
        }
        
        return ans;
    }
}
