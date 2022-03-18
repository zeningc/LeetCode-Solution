# 882. Reachable Nodes In Subdivided Graph
### Dijkstra's Algorithm
1. We can use Dijkstra to get all the reachable original nodes, calculate the sum of visited nodes, and record the shortest path to each node.
2. From each node, we can further extend through the edges that are not used during the first steps, so in first step we should annotate which edge is used in the shortest path. 

