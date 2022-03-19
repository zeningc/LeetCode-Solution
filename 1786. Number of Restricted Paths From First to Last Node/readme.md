# 1786. Number of Restricted Paths From First to Last Node
### Dijkstra + DFS Memorize
We can easily get $distanceToLastNode$ with Dijkstra algorithm, but what's the next?  
We could apply BFS, from the node 1, iteratively visit every possible node, until we find n. Visited nodes do not need to be marked, since we already restrict the path, if there is a path from a to b, then it is not possible for b to go back to a, since distanceToLastNode[a] > distanceToLastNode[b].  
However, BFS will cause TLE, the better way is to use DFS where we could apply memorize to improve the time complexity of it to O(N).  