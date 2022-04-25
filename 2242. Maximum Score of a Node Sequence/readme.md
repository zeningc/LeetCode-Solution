# 2242. Maximum Score of a Node Sequence
Iterate each edge, suppose we have an edge [a, b], find the node connected with a with the maximum score(x), and find the node connected with b with the maximum score(y). Note that x != ba && y != a. And the path with maximal score centered at the edge is scores[x] + scores[y] + scores[a] + scores[b].  
