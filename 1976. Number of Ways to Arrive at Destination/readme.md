# 1976. Number of Ways to Arrive at Destination
### Dijkstra + DFS Memorize
We can easily get the shortest path to each node with Dijkstra algorithm. Then we applied DFS + memorize to account for from each node how many path from this node can reach the end node with shortest distance.   