# 2212. Maximum Points in an Archery Competition
### 01 Knapsack
We can treat it as a 01 knapsack question —— if we want to get i point, we need to pay aliceArrows[i] + 1 arrows. Then we can restore the "path" from the dp array.  
- Time Complexity: O(12*10^5)
- Space Complexity: O(12*10^5)
Note that 1d Solution for Knapsack is not feasible since we need dp[i][j] to restore the path.  
### Burst Forced
Use 12-bit bitmask to represent the result, find the possible bitmask with maximum value, return the plan. 
- Time Complexity: O(2^12*12)
