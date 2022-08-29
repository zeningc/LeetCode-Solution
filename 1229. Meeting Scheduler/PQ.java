class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] slot : slots1)
            if (slot[1] - slot[0] >= duration)
                pq.offer(slot);
        for (int[] slot : slots2)
            if (slot[1] - slot[0] >= duration)
                pq.offer(slot);
        
        List<Integer> ans = new LinkedList<>();
        
        while (pq.size() > 1)   {
            int[] a = pq.poll();
            int[] b = pq.peek();
            
            if (b[0] + duration <= a[1])    {
                ans.add(b[0]);
                ans.add(b[0] + duration);
                return ans;
            }
        }
        
        return ans;
    }
}
