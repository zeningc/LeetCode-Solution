class Solution {
    public long singleDivisorTriplet(int[] nums) {
        long ans = 0;
        int[] cnt = new int[101];
        for (int num : nums)
            cnt[num]++;

        for (int i = 1; i <= 100; i++) {
            for (int j = i; cnt[i] > 0 && j <= 100; j++) {
                for (int k = j; cnt[j] > 0 && k <= 100; k++) {
                    if (cnt[k] == 0)
                        continue;
                    if (k == j && i == j)
                        continue;
                    int sum = i + j + k;
                    int dCnt = 0;
                    if (sum % i == 0)
                        dCnt++;
                    if (sum % j == 0)
                        dCnt++;
                    if (sum % k == 0)
                        dCnt++;
                    if (dCnt != 1)
                        continue;
                    if (i == j) {
                        ans += (long) cnt[i] * (cnt[i] - 1) * cnt[k] / 2;
                    } else if (j == k) {
                        ans += (long) cnt[i] * (cnt[k] - 1) * cnt[k] / 2;
                    } else {
                        ans += (long) cnt[i] * cnt[j] * cnt[k];
                    }
                }
            }
        }

        return ans * 6;
    }
}
