class Solution {
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        if (clips[0][0] != 0)
            return -1;
        int i = 0;
        int n = clips.length;
        int far = 0;
        while (i < n && far < time)    {
            int j = i;
            int nextFar = far;
            while (j < n && clips[j][0] <= far) {
                nextFar = Math.max(nextFar, clips[j][1]);
                j++;
            }
            far = nextFar;
            ans++;
            if (i == j)
                return -1;
            i = j;
        }
        
        return far < time ? -1 : ans;
    }
}