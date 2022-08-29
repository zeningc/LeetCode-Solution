class Solution {
    public boolean isPossible(int[] target) {
        int n = target.length;
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> b == a ? 0 : (b > a ? 1 : -1));
        long sum = 0;
        for (int i = 0; i < n; i++) {
            pq.offer((long)target[i]);
            sum += target[i];
        }
        
        while (pq.peek() != 1) {
            long pop = pq.poll();
            long others = sum - pop;
            
            if (others == 0 || pop - others <= 0)
                return false;
            
            long changed = pop % others;
            if (changed == 0)
                changed = others;
            sum = others + changed;
            pq.offer(changed);
        }
        
        return true;
    }
    
}
