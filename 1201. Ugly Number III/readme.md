# 1201. Ugly Number III 
## Binary Search + LCM
make a guess on the number, then check if there is k ugly number less than or equals the the number. Then the question is how to calculate the number of ugly number less than or equals to a given number N? the answer is $N/a + N/b + N/c - N/LCM(a, b) - N/LCM(b, c) - N/LCM(a, c) + N/LCM(a, b, c)$