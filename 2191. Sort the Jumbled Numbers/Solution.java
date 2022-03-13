class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            if (num == 0)   {
                map.put(0, mapping[0]);
                continue;
            }
            int mapped = 0;
            int idx = 1;
            int t = num;
            while (t != 0)  {
                mapped += idx * mapping[t % 10];
                idx *= 10;
                t /= 10;
            }
            map.put(num, mapped);
        }
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++)    
            idx[i] = i;
        
        Arrays.sort(idx, (a, b) -> map.getOrDefault(nums[a], 0) - map.getOrDefault(nums[b], 0));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) 
            ans[i] = nums[idx[i]];
        
        return ans;
    }
}