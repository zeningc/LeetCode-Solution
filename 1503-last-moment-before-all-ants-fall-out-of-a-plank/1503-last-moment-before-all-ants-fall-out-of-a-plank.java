class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int ret = 0;
        for (int num : left)
            ret = Math.max(ret, num);
        for (int num : right)
            ret = Math.max(ret, n - num);
        return ret;
    }
}