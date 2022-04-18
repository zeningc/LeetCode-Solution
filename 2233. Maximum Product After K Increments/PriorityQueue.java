class Solution {
    public int maximumProduct(int[] nums, int k) {
        long mod = (long)1e9 + 7;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums)
            pq.offer(num);
        
        for (int i = 0; i < k; i++) {
            int num = pq.poll();
            num++;
            pq.offer(num);
        }
        
        long ans = 1;
        while (!pq.isEmpty())   {
            int num = pq.poll();
            ans = (ans * num) % mod;
        }
        
        return (int) ans;
    }
}
