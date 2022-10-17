class Solution {
    Map<Pair<Integer, Integer>, Integer> mem;
    public List<Integer> replaceNonCoprimes(int[] nums) {
        mem = new HashMap<>();
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            while (!stack.isEmpty() && gcd(cur, stack.peek()) > 1)  {
                int pop = stack.pop();
                int lcm = (int)((long)cur * pop / gcd(cur, pop));
                cur = lcm;
            }
            stack.push(cur);
        }
        List<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty())
            ans.add(0, stack.pop());
        
        return ans;
    }
    
    int gcd(int m, int n)   {
        Pair<Integer, Integer> key = new Pair<Integer, Integer>(m, n);
        if (mem.containsKey(key))
            return mem.get(key);
        if (m < n)
            return gcd(n, m);
        if (m % n == 0)
            return n;
        int ans = gcd(n, m % n);
        mem.put(key, ans);
        return ans;
    }
}