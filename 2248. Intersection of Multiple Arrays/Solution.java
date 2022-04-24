class Solution {
    public List<Integer> intersection(int[][] nums) {
        int[] freq = new int[1001];
        for (int[] num : nums)    {
            for (int n : num)   {
                freq[n]++;
            }
        }
        
        List<Integer> ans = new LinkedList<>();
        
        for (int i = 0; i < 1001; i++)  {
            if (freq[i] == nums.length)
                ans.add(i);
        }
        
        return ans;
    }
}
