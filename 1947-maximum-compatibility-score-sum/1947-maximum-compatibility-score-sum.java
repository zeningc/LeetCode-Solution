class Solution {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length;
        int n = students[0].length;
        int[] studentArr = new int[m];
        int[] mentorArr = new int[m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (students[i][j] == 1)
                    studentArr[i] |= (1 << j);
                if (mentors[i][j] == 1)
                    mentorArr[i] |= (1 << j);
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {0, 0});
        boolean[] vis = new boolean[1 << m];
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int state = cur[0];
            if (vis[state])
                continue;
            vis[state] = true;
            int score = cur[1];
            if (state == (1 << m) - 1)
                return m * n - score;
            int j = cntBit(state);
            for (int i = 0; i < m; i++) {
                if ((state & (1 << i)) != 0)
                    continue;
                int newState = state | (1 << i);
                if (vis[newState])
                    continue;
                pq.offer(new int[] {newState, score + cntBit(studentArr[i] ^ mentorArr[j])});
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
}