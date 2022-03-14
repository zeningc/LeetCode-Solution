# 1627. Graph Connectivity With Threshold
### Burst Forced
Iterate each (i, j) pair, if their gcd is strictly larger than threshold, union them together, which makes the time complexity O(N^2) (will not be ACed).
However, we can only consider the factors that are larger than threshold, union f, f*2, f*3, ... together. The time complexity is complex, but it turns out to be approximately logN. 
## Reference
[1627.Graph-Connectivity-With-Threshold](https://github.com/wisdompeak/LeetCode/tree/master/Union_Find/1627.Graph-Connectivity-With-Threshold)