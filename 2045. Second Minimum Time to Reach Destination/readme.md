# 2045. Second Minimum Time to Reach Destination
### BFS
The trick is that we do not visit the same point if the time we arrived is the same, so that actually we will only visit each vertax at most for twice.

- Time Complexity: O(N^2)
- Space Complexity: O(N^2)