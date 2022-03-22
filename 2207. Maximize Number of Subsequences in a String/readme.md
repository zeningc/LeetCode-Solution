# 2207. Maximize Number of Subsequences in a String
## Greedy
if pattern[0] == pattern[1], the answer should be freq * (freq + 1) / 2
otherwise, the maximum number of subsequence should be created in either of the following cases:
1. pattern[0] being placed before  the first character of text
2. pattern[1] being place after the last character of text