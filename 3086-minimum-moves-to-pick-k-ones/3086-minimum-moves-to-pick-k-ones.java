class Solution {
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int[] cnt1 = new int[n];
        for (int i = 0; i < nums.length; i++)
            cnt1[i] = (i == 0 ? 0 : cnt1[i - 1]) + (nums[i] == 0 ? 0 : 1);

        int[] cnt2 = new int[n];
        for (int i = n - 1; i >= 0; i--)
            cnt2[i] = (i == n - 1 ? 0 : cnt2[i + 1]) + (nums[i] == 0 ? 0 : 1);

        long[] presum1 = new long[n];

        for (int i = 0; i < n; i++)
            presum1[i] = i == 0 ? 0 : presum1[i - 1] + cnt1[i - 1];

        long[] presum2 = new long[n];

        for (int i = n - 1; i >= 0; i--)
            presum2[i] = i == n - 1 ? 0 : presum2[i + 1] + cnt2[i + 1];

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, cntMove(cnt1, cnt2, presum1, presum2, nums, i, k, maxChanges));
        }
        return ans;
    }

    long cntMove(int[] cnt1, int[] cnt2, long[] presum1, long[] presum2,  int[] nums, int i, int k, int maxChanges)   {
        long cur = 0;
        int n = nums.length;
        int init = 0;
        if (nums[i] == 1)   {
            init++;
            k--;
        }
        int consume = 0;
        if (k > 0 && i > 0 && nums[i - 1] == 1)  {
            cur++;
            k--;
            consume++;
        }
        if (k > 0 && i < n - 1 && nums[i + 1] == 1)  {
            cur++;
            k--;
            consume++;
        }

        if (k == 0)
            return cur;

        int usedChanges = Math.min(k, maxChanges);
        k -= usedChanges;
        cur += usedChanges * 2;

        if (k == 0)
            return cur;

        int lo = 0;
        int hi = Math.max(i + 1, n - i);

        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (check(cnt1, consume + init, i, mid, k))
                hi = mid - 1;
            else
                lo = mid + 1;
        }

        int r = lo;

        cur += checkLeft(presum1, cnt1, i - r + 1, i);
        cur += checkRight(presum2, cnt2, i, i + r - 1);

        return cur - consume;
    }

    boolean check(int[] cnt, int consume, int i, int r, int k) {
        return getCnt(cnt, i - r + 1, i + r - 1) - consume >= k;
    }


    int getCnt(int[] cnt, int lo, int hi)   {
        if (hi >= cnt.length)
            hi = cnt.length - 1;
        if (lo < 0)
            lo = 0;
        return cnt[hi] - (lo == 0 ? 0 : cnt[lo - 1]);
    }

    long checkLeft(long[] presum1, int[] cnt1, int lo, int hi)  {
        int n = presum1.length;
        if (hi >= n)
            hi = n - 1;
        if (lo < 0)
            lo = 0;
        return presum1[hi] - (lo == 0 ? 0 : presum1[lo - 1]) - (lo == 0 ? 0 : (long)cnt1[lo - 1] * (hi - lo + 1));
    }

    long checkRight(long[] presum2, int[] cnt2, int lo, int hi)  {
        int n = presum2.length;
        if (hi >= n)
            hi = n - 1;
        if (lo < 0)
            lo = 0;
        return presum2[lo] - (hi == n - 1 ? 0 : presum2[hi + 1]) - (hi == n - 1 ? 0 : (long)cnt2[hi + 1] * (hi - lo + 1));
    }
}