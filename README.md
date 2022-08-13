# LeetCode-Solution

My own implementation for leetcode questions in Java.

[My LeetCode Homepage](https://leetcode.com/zeningc/)  
[My Linkedin Profile](https://www.linkedin.com/in/zening99/)(currently seeking for full-time SDE opportunities in US, any help would be appreciated)

## Classification -- WIP
> Note that the classification only means that a question can be solved with a specific method, there is no gurantee that it is the optimized solution.
### DP
#### Time Sequence
> for the following questions, the state on time i only depends on i - 1
- 198. House Robber
- 213. House Robber II
- 123. Best Time to Buy and Sell Stocks III
- 309. Best Time to Bug and Sell Stock with Cooldown
- 376. Wiggle Subsequence
- 256. Paint House
- 487. Max Consecutive Ones II
- 1186. Maximum Subarray Sum with One Deletion

#### Time Sequence II
> for the following questions, the state on time i only depends on [0, i - 1]
- 300. LIS
- 368. Largest Divisible Subset
- 1105. Filling Bookcase Shelves

#### Dual Sequence
> for the following questions, there are always 2 string(sometimes 1 but related to palindrome), and dp[i][j] depends on dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]
> Most of the questions are related to LCS / SCS
- 1143. Longest common Subsequences
- 1092. Shortest Common Supersequences
- 72. Edit Distance
- 97. Interleaving String
- 115. Distinct Subsequences
- 727. Minimum Window Subsequence
- 583. Delete Operation for Two Strings
- 712. Minimum ASCII Delete Sum for Two Strings
- 1035. Uncrossed Lines
- 1216. Valid Palindrome III
- 1312. Minimum Insertion Steps to Make a String Palindrome

#### Range DP
> the following questions always provide a sequence and ask you to divide it into K consecutive range
- 1278. Palindrome Partitioning III
- 813. Largest Sum of Averages
- 410. Split Array Largest Sum
- 1335. Minumum Difficulty of a Job Schedule

#### Range DP II
> for the following questions, the value of long range depends on short range. Some palindrome-related question falls in both this type and Dual Sequence, because if we reverse the string and treat it as a new string, we can perform SCS, LCS, etc.
- 516. Longest Palindrome Subsequence
- 312. Burst Ballons
- 375. Guess Number Higher or Lower II
- 1246. Palindrome Removal

#### Range DP and Range DP II
- 1000. Minimum Cost to Merge Stones

#### Knapsack
> classical questions, will spend some time to further classify different knapsack
- 494. Target Sum
- 1094. Last Stone Weight II
- 474. Ones and Zeros
- 879. Profitable Schemes
- 956. Tallest Billboard

#### BitMask DP
> most of the following questions can be solve by DFS + Mem. 
- 691. Stickers to Spell Word
- 1125. Smallest Sufficient Team
- 1349. Maximum Students Taking Exam

### Differential Array
- 370. Range Addition

### HashMap
- 974. Subarray Sums Divisible by K

## Question Source:
- LeetCode Weekly Contest
- [Blind 75](https://leetcode.com/discuss/general-discussion/460599/blind-75-leetcode-questions)
- Daily Question of [CruelCoding Group](http://board.cruelcoding.com/)
