class Solution {
    public long kSum(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++)   {
            if (nums[i] > 0)    {
                sum += nums[i];
                nums[i] = -nums[i];
            }
        }
        if (k == 1)
            return sum;
        Arrays.sort(nums);
        reverse(nums);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? 0 : (b[0] > a[0] ? 1 : -1));
        pq.offer(new long[] {sum + nums[0], 0});
        long ans = 0;
        for (int i = 1; i < k; i++) {
            long[] curr = pq.poll();
            long currSum = curr[0];
            int idx = (int)curr[1];
            if (idx + 1 < nums.length)  {
                pq.offer(new long[] {currSum + nums[idx + 1], idx + 1});
                pq.offer(new long[] {currSum + nums[idx + 1] - nums[idx], idx + 1});
            }
            ans = currSum;
        }
        
        return ans;
    }
    
    void reverse(int[] nums)    {
        int i = 0;
        int j = nums.length - 1;
        while (i < j)   {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }
}
