class Solution {
    public int[] topKFrequent(int[] nums, int k) {
         Map<Integer, Integer> freq = new HashMap<>();
        
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for (Map.Entry<Integer, Integer> e : freq.entrySet())   {
            pq.offer(new int[] {e.getKey(), e.getValue()});
            if (pq.size() > k)
                pq.poll();
        }
        
        int[] ans = new int[k];
        for (int i = 0; i < k; i++)
            ans[i] = pq.poll()[0];
        
        return ans;
    }
}
