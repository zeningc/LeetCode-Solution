class Solution {
    public int countCompleteSubstrings(String word, int k) {
        int lo = 0;
        int n = word.length();
        int ans = 0;
        int[][] presum = new int[26][n + 1];
        for (int hi = 0; hi < n; hi++)  {
            char c = word.charAt(hi);
            char prevCh = word.charAt(hi == 0 ? hi : hi - 1);
            for (int i = 0; i < 26; i++)
                presum[i][hi + 1] = presum[i][hi] + (i == c - 'a' ? 1 : 0);
            if (Math.abs(prevCh - c) > 2)
                lo = hi;
            for (int i = 1; i <= 26 && i * k <= hi - lo + 1; i++)   {
                boolean valid = true;
                for (int j = 0; j < 26; j++)    {
                    int cnt = presum[j][hi + 1] - presum[j][hi - i * k + 1];
                    if (cnt != 0 && cnt != k) {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    ans++;
            }
        }
        
        return ans;
    }
}

// xxxxxxx|xxx|xxxxxxx

// presum[a-z][idx]

// k 2*k ... 26*k

// 26*k*n
