class Solution {
    int mod = (int)1e9 + 7;
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++)
            scores[i] = getScore(nums.get(i));
        List<long[]> scoreList = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || scores[stack.peek()] < scores[i]))   {
                int pop = stack.pop();
                int lo = stack.isEmpty() ? -1 : stack.peek();
                int hi = i;
                scoreList.add(new long[] {nums.get(pop), (long)(pop - lo) * (hi - pop)});
            }
            stack.push(i);
        }
        Collections.sort(scoreList, (a, b) -> b[0] - a[0] == 0 ? 0 : (b[0] - a[0] > 0 ? 1 : -1));
        long ans = 1;
        int idx = 0;
        while (k > 0 && idx < n)   {
            long consume = Math.min(k, scoreList.get(idx)[1]);
            ans = (ans * quickPow(scoreList.get(idx)[0], consume)) % 1000000007;
            k -= consume;
            idx++;
        }
        
        return (int)ans;
    }
    
    long quickPow(long x, long y) {
        long ans = 1;
        long power = x;
        for (int i = 0; i < 32; i++)    {
            if ((y & (1 << i)) != 0)    {
                ans = (ans * power) % 1000000007;
            }
            power = (power * power) % 1000000007;
        }
        
        return ans;
    }
    
    int getScore(int num)   {
        int n = (int)Math.sqrt(num);
        int cnt = 0;
        for (int i = 2; i <= n; i++)    {
            if (num % i != 0)
                continue;
            cnt++;
            while (num % i == 0)
                num /= i;
        }
        if (num != 1)
            cnt++;
        return cnt;
    }
}