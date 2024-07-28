class Solution:
    MOD = 1000000007

    def rearrangeSticks(self, n: int, k: int) -> int:
        dp = [1] + ([0] * k)
        for i in range(1, n + 1):
            for j in range(k, 0, -1):
                dp[j] *= n - i
                dp[j] += dp[j - 1]
                dp[j] %= Solution.MOD
            dp[0] *= n - i
            dp[0] %= Solution.MOD
        return dp[k]