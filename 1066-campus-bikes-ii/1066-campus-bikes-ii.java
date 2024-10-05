class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] vis = new boolean[1 << m];
        
        pq.offer(new int[] {0, 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int state = cur[0];
            if (vis[state])
                continue;
            vis[state] = true;
            int sum = cur[1];
            
            int j = cntBit(state);
            if (j == n)
                return sum;
            
            for (int i = 0; i < m; i++) {
                if ((state & (1 << i)) != 0)
                    continue;
                int newState = state | (1 << i);
                if (vis[newState])
                    continue;
                pq.offer(new int[] {newState, sum + calc(workers[j], bikes[i])});
            }
        }
        
        return -1;
    }
    
    int cntBit(int state)   {
        int cnt = 0;
        while (state != 0)  {
            cnt++;
            state &= (state - 1);
        }
        return cnt;
    }
    
    int calc(int[] a, int[] b)  {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}