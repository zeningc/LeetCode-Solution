class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        long far = Integer.MIN_VALUE;
        int len = 0;
        for (int[] pair : pairs)    {
            if (pair[0] > far)  {
                far = pair[1];
                len++;
            }
            else if (pair[1] < far) {
                far = pair[1];
            }
        }
        return len;
    }
}