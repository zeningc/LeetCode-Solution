class Solution {
    public int makePrefSumNonNegative(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long prefix = 0;
        int ans = 0;
        for (int num : nums)    {
            if (num >= 0)   {
                prefix += num;
                pq.offer(num);
                continue;
            }
            pq.offer(num);
            prefix += num;
            if (prefix>= 0)
                continue;
            prefix -= pq.poll();
            ans++;
        }
        return ans;
    }
}