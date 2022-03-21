class Solution {
    public int countCollisions(String directions) {
        char[] d = directions.toCharArray();
        int n = directions.length();
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (d[i] == 'R' && d[i + 1] == 'L' || d[i] == 'R' && d[i + 1] == 'S' || d[i] == 'S' && d[i + 1] == 'L') {
                ans += (d[i] == 'S' ? 0 : 1) + (d[i + 1] == 'S' ? 0 : 1);
                d[i] = 'S';
                d[i + 1] = 'S';
            }
        }
        
        for (int i = n - 1; i > 0; i--) {
            if (d[i - 1] == 'R' && d[i] == 'S') {
                ans++;
                d[i - 1] = 'S';
            }
        }
        
        return ans;
    }
}

