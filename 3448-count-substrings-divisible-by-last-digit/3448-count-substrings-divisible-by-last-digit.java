class Solution {
    public long countSubstrings(String s) {
        long ans = 0;
        for (int d = 1; d <= 9; d++)    {
            int[] cnt = new int[d];
            int r = 0;
            cnt[0] = 1;
            for (char c : s.toCharArray())  {
                int[] newCnt = new int[d];
                for (int i = 0; i < d; i++) {
                    newCnt[(i * 10) % d] += cnt[i];
                }
                int num = c - '0';
                r = (r * 10 + num) % d;
                if (num == d)
                    ans += newCnt[r];
                newCnt[r]++;
                cnt = newCnt;
            }
        }

        return ans;
    }
}