class Solution {
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0)
            return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y)  {
            int t = x;
            x = y;
            y = t;
        }        
        int[][] dir = new int[][] {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        Deque<int[]> sQ = new LinkedList<>();
        Deque<int[]> eQ = new LinkedList<>();
        Set<Long> sVis = new HashSet<>();
        Set<Long> eVis = new HashSet<>();
        int minX = -2;
        int minY = -2;
        int maxX = x + 2;
        int maxY = y + 2;
        sQ.offer(new int[] {0, 0});
        eQ.offer(new int[] {x, y});
        sVis.add(getKey(0, 0));
        eVis.add(getKey(x, y));
        int step = 1;
        while (!sQ.isEmpty() && !eQ.isEmpty())  {
            Deque<int[]> tQ = eQ;
            Set<Long> vis = eVis;
            Set<Long> vis2 = sVis;
            if (sQ.size() < eQ.size())  {
                tQ = sQ;
                vis = sVis;
                vis2 = eVis;
            }

            int size = tQ.size();
            while (size-- > 0)  {
                int[] cur = tQ.poll();
                int cx = cur[0];
                int cy = cur[1];

                for (int[] d : dir) {
                    int nx = cx + d[0];
                    int ny = cy + d[1];
                    long nk = getKey(nx, ny);
                    if (nx < minX || nx > maxX || ny < minY || ny > maxY || vis.contains(nk))
                        continue;
                    vis.add(nk);
                    if (vis2.contains(nk))
                        return step;
                    tQ.offer(new int[] {nx, ny});
                }
            }

            step++;
        }

        return -1;
    }

    long getKey(long x, long y) {
        return (x << 32) + (y & 0xffffffffL);
    }
}