# 2101. Detonate the Maximum Bombs
Construct a graph and start BFS from each node. 
```
Note that we need to start from each point(O(N))
    from each point we need to perform a BFS(O(N))
        within each step in BFS we need to iterate every bomb related to current bombs(O(N))
```
As a result,  
- Time Complexity: O(N^3)
- Space Complexity: O(N^2) for the graph