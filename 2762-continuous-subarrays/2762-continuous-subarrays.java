class Solution {
    public long continuousSubarrays(int[] nums) {
        long ans = 0;
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        
        int lo = 0;
        for (int hi = 0; hi < nums.length; hi++)  {
            pq1.offer(hi);
            pq2.offer(hi);

            while (pq1.peek() < lo)
                pq1.poll();

            while (pq2.peek() < lo)
                pq2.poll();

            while (nums[pq2.peek()] - nums[pq1.peek()] > 2) {
                lo++;
                
                while (pq1.peek() < lo)
                    pq1.poll();

                while (pq2.peek() < lo)
                    pq2.poll();
            }
            
            ans += hi - lo + 1;
            
        }
        
        
        return ans;
    }
}

