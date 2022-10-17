class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        int maxEle = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++)   {
            if (!first.containsKey(nums[i]))
                first.put(nums[i], i);
            last.put(nums[i], i);
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> e : freq.entrySet())   {
            if (e.getValue() > maxFreq || e.getValue() == maxFreq && last.get(e.getKey()) - first.get(e.getKey()) + 1 < ans)  {
                maxFreq = e.getValue();
                ans = last.get(e.getKey()) - first.get(e.getKey()) + 1;
            }
        }
        
        return ans;
    }
}
