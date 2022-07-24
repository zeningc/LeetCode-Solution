class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        int ans = 0;
        
        for (int num : nums)    {
            if (set.contains(num - 1))
                continue;
            
            int len = 0;
            for (int i = num; set.contains(i); i++) {
                len++;
            }
            
            ans = Math.max(ans, len);
        }
        
        return ans;
    }
}
