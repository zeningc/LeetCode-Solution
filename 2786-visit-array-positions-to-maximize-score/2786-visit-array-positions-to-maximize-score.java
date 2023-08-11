class Solution {
    public long maxScore(int[] nums, int x) {
        long ans = 0;
        long odd = nums[0] % 2 == 1 ? nums[0] : Integer.MIN_VALUE;
        long even = nums[0] % 2 == 0 ? nums[0] : Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            boolean isOdd = nums[i] % 2 == 1;
            long preOdd = odd + nums[i] + (isOdd ? 0 : -x);
            long preEven = even + nums[i] + (isOdd ? -x : 0);
            long cur = Math.max(preOdd, preEven);
            if (isOdd)
                odd = Math.max(cur, odd);
            else
                even = Math.max(cur, even);
        }
        return Math.max(odd, even);
    }
}

