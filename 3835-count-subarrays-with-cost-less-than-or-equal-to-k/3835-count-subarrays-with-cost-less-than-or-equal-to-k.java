class Solution {
    public long countSubarrays(int[] nums, long k) {
        long ans = 0;
        PriorityQueue<int[]> minQ = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> maxQ = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int lo = 0;
        int n = nums.length;
        for (int hi = 0; hi < n; hi++)  {
            minQ.offer(new int[] {hi, nums[hi]});
            maxQ.offer(new int[] {hi, nums[hi]});
            while (!minQ.isEmpty() && !maxQ.isEmpty() && ((long)maxQ.peek()[1] - minQ.peek()[1]) * (hi - lo + 1) > k)  {
                lo++;
                while (!minQ.isEmpty() && minQ.peek()[0] < lo)
                    minQ.poll();
                while (!maxQ.isEmpty() && maxQ.peek()[0] < lo)
                    maxQ.poll();
            }

            ans += hi - lo + 1;
        }

        return ans;
    }
}