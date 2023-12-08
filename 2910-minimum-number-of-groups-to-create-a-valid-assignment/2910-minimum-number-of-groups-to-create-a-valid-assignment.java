class Solution {
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        Arrays.stream(nums).forEach(num -> m.put(num, m.getOrDefault(num, 0) + 1));
        int hi = m.values().stream().max(Integer::compareTo).orElse(0);
        int ans = nums.length;
        for (int i = hi; i >= 1; i--)  {
            int total = 0;
            boolean valid = true;
            for (int freq : m.values())  {
                int groupCnt = freq / i;
                int r = freq % i;
                if (r == 0) {
                    total += groupCnt;
                    continue;
                }
                
                if (groupCnt >= i - 1 - r)  {
                    total += groupCnt + 1;
                    continue;
                }
                
                valid = false;
                break;
            }
            
            if (valid)  {
                ans = total;
                break;
            }
            
        }
        
        return ans;
    }
}



