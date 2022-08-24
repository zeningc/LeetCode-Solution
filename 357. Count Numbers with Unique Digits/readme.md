# 357. Count Numbers with Unique Digits
Digit DP is an overkill, actually we can just return A(10,n)-A(9,n-1) with n == 0 being the cornor case.