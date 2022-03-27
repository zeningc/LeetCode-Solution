# 2218. Maximum Value of K Coins From Piles
When dealing with getting N items from a pile, a prefix sum can be applied.  
And then the problem could be solved using DP:  
```
dp[i][j] = max(dp[i][j], dp[i - 1][j - x] + sum(piles[i][:x]))
for i in [0, piles.size() - 1]
for j in [1, k]
for x in min(piles[i].size(), j)
```