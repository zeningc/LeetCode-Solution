class Solution {
    long mod = 1000000007;
    public int totalStrength(int[] strength) {
        int n = strength.length;
        long[] nPresum = new long[n + 1];
        long[] presum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            nPresum[i + 1] = (nPresum[i] + ((long)(i + 1) * strength[i]) % mod) % mod;
            presum[i + 1] = (presum[i] + strength[i]) % mod;
        }
        long ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || strength[stack.peek()] >= strength[i]))   {
                int pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                long leftOrderSum = minus(minus(nPresum[pop], nPresum[left + 1]), multiple(minus(presum[pop], presum[left + 1]), (left + 1)));
                long rightOrderSum = minus(multiple(minus(presum[right], presum[pop + 1]), right + 1), minus(nPresum[right], nPresum[pop + 1]));
                long leftSum =  multiple(multiple(leftOrderSum, (right - pop)), strength[pop]);
                long rightSum = multiple(multiple(rightOrderSum, (pop - left)), strength[pop]);
                long midSum = multiple(multiple(multiple((right - pop), (pop - left)), strength[pop]), strength[pop]);
                ans = add(add(add(ans, leftSum), midSum), rightSum);
            }
            stack.push(i);
        }
        
        return (int)ans;
    }
    
    long add(long a, long b)    {
        return (a % mod + b % mod) % mod;
    }
    
    long minus(long a, long b)  {
        return (mod + a % mod - b % mod) % mod;
    }
    
    long multiple(long a, long b)   {
        return (a % mod * b % mod) % mod;
    }
    
}
/*
2 * nums[3] + 3 * nums[4] + 4 * nums[5]
right = 6
right - 1

[a, b, [c], d]
3 * 2 = 6
abcd
abc
bcd
bc
cd
c

a = 2
b = 4
c = 6
d = 3

right * a + right * 2 * b + ... (right * left) * c + ... (left * (right - 1)) * ...
1 * a + 2 * b + ...

i * nums[i]
5 * a + 6 * b + 7 * c
(1 * a + 1 * b + 1 * c) * 4

8 * a + 8 * b + 8 * c
5 * a + 6 * b + 7 * c



*/