class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            int same = 0;
            Map<Long, Integer> cnt = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                if (x1 == x2 && y2 == y1)   {
                    same++;
                    continue;
                }
                int dy = y1 - y2;
                int dx = x1 - x2;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }
                long key;
                if (dx == 0)    {
                    key = getKey(1, 0);
                }
                else if (dy == 0)   {
                    key = getKey(0, 1);
                }
                else    {
                    int g = gcd(dx, dy);
                    key = getKey(dy / g, dx / g);
                }

                cnt.put(key, cnt.getOrDefault(key, 0) + 1);
                ans = Math.max(ans, cnt.get(key) + same + 1);
            }
        }

        return ans;
    }

    int gcd(int a, int b)   {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    long getKey(long x, long y)    {
        return (x << 32) + (0xffffffffL & y);
    }
}