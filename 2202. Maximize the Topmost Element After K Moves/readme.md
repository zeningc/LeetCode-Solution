# 2202. Maximize the Topmost Element After K Moves
We need to consider the following situation:
### k < n
Let the very first part of nums be [0, 1, 2, 3, ..., k-1](ignore the other remaining elements), we can easily notice that [0, k-2] is always reachable(use k - 1 steps to pop until only k-1 is left, then add back the desired value). However, k - 1 cannot be reached. K is also reachable by poping [0, k - 1]. 

### k == n
Only the last element of nums is not reachable

## k > n
We can pop one element, and then add it back. This process can help us decrease k without changing nums. In the end, there will be two different situations:  
1. k == n + 1, we can pop everything out and add back the desired element.
2. k == n + 2, we can pop everything out, add one random element back and then add back the desired element.


To conclude, when k <= n, the only constraint is that the kth element is not reachable, then the maximum topmost is max(nums[0], ..., nums[k - 2]). When k > n, we can choose the maximum element in nums. 
Note that there are some edge cases. Like k == 1, n == 1, k == 0.

My implmentation in contect used sorting. However, sorting is actually not necessary since we only need the maximum value of it. 

- Time Complexity: O(K)
- Space Complexity: O(1)