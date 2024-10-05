class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        boolean[] vis = new boolean[1 << m];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {0, 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int state = cur[0];
            int sum = cur[1];
            if (vis[state])
                continue;
            vis[state] = true;
            if (state == (1 << m) - 1)
                return sum;
            int j = countBit(state);
            for (int i = 0; i < m; i++) {
                if ((state & (1 << i)) != 0)
                    continue;
                
                int newState = state | (1 << i);
                if (vis[newState])
                    continue;
                
                pq.offer(new int[] {newState, sum + (nums1[i] ^ nums2[j])});
            }
        }
        
        return -1;
    }
    
    int countBit(int state) {
        int cnt = 0;
        while (state != 0)  {
            cnt++;
            state &= (state - 1);
        }
        return cnt;
    }
}

/*
0 1 
0 1


*/