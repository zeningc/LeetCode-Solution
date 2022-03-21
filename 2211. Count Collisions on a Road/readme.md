# 2211. Count Collisions on a Road
Note that there exists one situation: RRRRS, when we iterate from start to end, and change the sequence to RRRSS, the R before the collision will also collide.  
To account for all collision, iteration from backward is needed. 
There exists better soluition with Space Complexity(O(1)), the underlying point is that we could imagine the sequence as "LLL[XXXXXXX]RRR", if we get rid of the margin L on the left side and R on the right side, in the [XXXXXX] sequence, each car that is not S will cause collision. 
### Reference
[Easy understanding - TC : O(N), SC - O(1)](https://leetcode.com/problems/count-collisions-on-a-road/discuss/1865669/Easy-understanding-TC-%3A-O(N)-SC-O(1))