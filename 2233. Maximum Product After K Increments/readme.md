# 2233. Maximum Product After K Increments
It can be proved that the by increasing the smallest factor, we can get the best product.  
## Priority Queue
Get the smallest element, increase it and add it back
## Binary Search
Sort the whole array, try to get the index that all the previous indexes can be filled to arr[index] but [0, index] cannot be filled to arr[index + 1]  
## Complexity
- Time Complexity: O(NlogN)  
- Space Complexity: O(N) for priority queue, and binary search if considered the sorting.   
