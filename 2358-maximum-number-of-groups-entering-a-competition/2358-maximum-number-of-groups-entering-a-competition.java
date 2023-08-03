class Solution {
    public int maximumGroups(int[] grades) {
        Arrays.sort(grades);
        int cnt = 0;
        int preSum = -1;
        int sum = 0;
        int preLen = 0;
        int len = 0;
        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
            len++;
            if (sum > preSum && len > preLen)   {
                cnt++;
                preSum = sum;
                preLen = len;
                sum = 0;
                len = 0;
            }
        }
        return cnt;
    }
}