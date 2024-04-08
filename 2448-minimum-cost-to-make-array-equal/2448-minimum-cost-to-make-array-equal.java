class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        List<int[]> numsList = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            set.add(nums[i]);
        for (int i = 0; i < n; i++)
            numsList.add(new int[] {nums[i], cost[i]});
        
        Collections.sort(numsList, (a, b) -> a[0] - b[0]);
        
        long[] presum = new long[n + 1];
        long[] preCost = new long[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            presum[i] = presum[i - 1] + (long)numsList.get(i - 1)[0] * numsList.get(i - 1)[1];
            preCost[i] = preCost[i - 1] + (long)numsList.get(i - 1)[1];
        }
        
        long ans = Long.MAX_VALUE;
        
        for (int num : set) {
            int left = 0;
            int right = n - 1;
            while (left <= right)   {
                int mid = left + (right - left) / 2;
                if (numsList.get(mid)[0] > num) {
                    right = mid - 1;
                }
                else    {
                    left = mid + 1;
                }
            }
            long currCost = (presum[n] - presum[left]) - (preCost[n] - preCost[left]) * num;
            currCost += preCost[left] * num - presum[left];
            ans = Math.min(ans, currCost);
        }
        return ans;
    }
    
    
}