# 2217. Find Palindrome With Fixed Length
The number of positive palindrome number can be calculated before our computation. Let's assume we have a palindrome number with 5 digits: 
[x [y [z] y] x]  
What is the total number of positive palindrome number of 5 digit? It is ``` palinromeCntOfLen(1) * palindromeCntOfLen(2) * 9 ```. Generally speaking, given a fix length ```N```, the number of positive palindrome number would be 9 * palinromeCntOfLen(N - 2) * ... * palinromeCntOfLen(1 / 2 depending on the N).  
So given a query, we can decide if we have the number of positive palindrome >= query, if not, the ans is -1. If it is, we can decide the periphery number, which is ``` query / palinromeCntOfLen(N - 2) ```. Continue this process until N == 1 or N == 2, in which case we can determine the inside palidrome.  
Note that in the most perophery layer, we cannot fill 0, since 0 x y x 0 is actually x y x. So the recursive process is different in the outer layer. 

