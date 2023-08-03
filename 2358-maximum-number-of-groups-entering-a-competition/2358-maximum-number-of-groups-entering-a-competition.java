class Solution {
    public int maximumGroups(int[] grades) {
        int ans = 1;
        for (int i = 2; i <= Math.sqrt(grades.length * 2); i++)  {
            if (i * (i + 1) / 2 <= grades.length)
                ans = i;
            else
                break;
        }
        return ans;
    }
}

// n  * (n + 1) / 2 <= grades.length
// (n + 1) ^ 2 <= m * 2