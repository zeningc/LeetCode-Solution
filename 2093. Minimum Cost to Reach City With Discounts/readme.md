# 2093. Minimum Cost to Reach City With Discounts
## Dijkstra
Note that the arriving at the same node with different discount chance left is actually different situation, we need vis[n][discounts] instead of a single vis[n]. 