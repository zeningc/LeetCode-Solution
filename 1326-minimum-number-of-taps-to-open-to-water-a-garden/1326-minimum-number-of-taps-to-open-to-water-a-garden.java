class Solution {
    public int minTaps(int n, int[] ranges) {
        List<int[]> rangeList = new ArrayList<>();
        for (int i = 0 ; i < n + 1; i++)
            rangeList.add(new int[] {i - ranges[i], i + ranges[i]});
        Collections.sort(rangeList, (a, b) -> a[0] - b[0]);
        int i = 0;
        int far = 0;
        int ans = 0;
        while (i < n + 1 && far < n)   {
            int j = i;
            int nextFar = far;
            while (j < n + 1 && rangeList.get(j)[0] <= far) {
                nextFar = Math.max(nextFar, rangeList.get(j)[1]);
                j++;
            }
            far = nextFar;
            ans++;
            if (i == j)
                return -1;
            i = j;
        }
        return far < n ? -1 : ans;
    }
}