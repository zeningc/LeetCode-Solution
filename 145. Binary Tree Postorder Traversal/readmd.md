# 145. Binary Tree Postorder Traversal
Things begin to be tricky when it comes to postorder traversal.
- DFS: nothing change
- Stack: we need to repush the node into stack if we haven't seen it
- Morris: maybe it is not the purpose of Morris but we can still do it, after removing the link from the rightmost child of p's left child, add the "linked list" from p's left child to its rightmost child to answer reversely.