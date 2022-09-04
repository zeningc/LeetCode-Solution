class Solution {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        
        int prev = -1;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1)   {
                if (prev == -1)
                    ans = i - prev - 1;
                else
                    ans = Math.max(ans, (i - prev) / 2);
                prev = i;
            }
        }
        
        return Math.max(ans, n - prev - 1);
    }
}
