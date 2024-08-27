class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        int m = queries.length;
        int[] leftToRight = new int[n];
        int[] rightToLeft = new int[n];
        int cnt = 0;
        int lo = 0;
        int hi = 0;
        for (; hi < n; hi++) {
            if (s.charAt(hi) == '1')
                cnt++;
            while (cnt > k && hi - lo + 1 - cnt > k)    {
                if (s.charAt(lo) == '1')
                    cnt--;
                lo++;
            }
            rightToLeft[hi] = lo;
        }
        lo = n - 1;
        hi = n - 1;
        cnt = 0;
        for (; lo >= 0; lo--)   {
            if (s.charAt(lo) == '1')
                cnt++;
            while (cnt > k && hi - lo + 1 - cnt > k)    {
                if (s.charAt(hi) == '1')
                    cnt--;
                hi--;
            }
            leftToRight[lo] = hi;
        }
        
        long[] presum = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            presum[i] = presum[i - 1] + ((i - 1) - rightToLeft[i - 1] + 1);
        }
        
        long[] ans = new long[m];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int left = query[0];
            int right = query[1];
            int mid = Math.min(right, leftToRight[left]);
            int len = mid - left + 1;
            long curr = (long)len * (len + 1) / 2;
            curr += presum[right + 1] - presum[mid + 1];
            ans[i] = curr;
        }
        
        return ans;
    }
}