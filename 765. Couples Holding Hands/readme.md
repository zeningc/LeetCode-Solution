# 765. Couples Holding Hands
### Greedy
Randomly make 1 swap to let at least 1 couple sitting together, continue doing this process until all the swaps made form a "ring". Then search for the other mismatched couples. 
### UnionFind
Use UnionFind to union each couples, then union each pair in the subsequent indexes. We can then map people into groups where swap between group can make couple in groups match. 
In a group containing k couples(2*k people), we need exactly k - 1 swap to make every couple sitting side by side. Suppose we have the following union:  
[AB CD EF]  
In the woest case, we need 2 swaps to make every couple sitting together(each swap makes 1 couple sitting together, and when 2 of them is sitting together, the remaining couple will also be sitting together).  
But could we only make 1 swap? You can see that if we could use 1 swap to make all 3 couples sitting together, 1 of the couples will not be necessarily in this group. 