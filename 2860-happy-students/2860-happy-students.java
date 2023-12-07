class Solution {
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int i = 0;
        int n = nums.size();
        int ans = 0;
        while (i < n)   {
            int j = i;
            while (j < n && nums.get(i) == nums.get(j))
                j++;
            
            if (j < n && j > nums.get(i) && j < nums.get(j))
                ans++;
            
            i = j;
        }
        
        return ans + (nums.get(0) > 0 ? 1 : 0) + (nums.get(n - 1) < n ? 1 : 0);
    }
}