class Solution {
    Map<String, Integer> cache;
    public int maxScore(int[] nums) {
        cache = new HashMap<>();
        int n = nums.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        List<Integer> states = new ArrayList<>();
        
        int temp = (1 << 2) - 1;
        while (temp < (1 << n))   {
            states.add(temp);
            int c = temp & -temp;
            int r = temp + c;
            temp = (((temp ^ r) >> 2) / c) | r;
        }
        
        for (int i = 0; i < n / 2; i++) {
            for (int state = (1 << n) - 1; state >= 0; state--)   {
                if (countBit(state) != (i + 1) * 2)
                    continue;
                for (int cur : states)  {
                    if ((state & cur) != cur || dp[state - cur] == Integer.MIN_VALUE)
                        continue;
                    dp[state] = Math.max(dp[state], dp[state - cur] + (i + 1) * calc(nums, cur));
                }
            }
        }
        
        return dp[(1 << n) - 1];
    }
    
    int countBit(int state) {
        int cnt = 0;
        while (state != 0)  {
            cnt++;
            state &= (state - 1);
        }
        return cnt;
    }
    
    int calc(int[] nums, int state) {
        int i = -1;
        int j = -1;
        for (int k= 0; k < nums.length * 2; k++)
            if ((state & (1 << k)) != 0)
                if (i == -1)
                    i = k;
                else if (j == -1)
                    j = k;
        return gcp(nums[i], nums[j]);
    }
    
    int gcp(int n1, int n2) {
        String key = n1 > n2 ? n2 + "_" + n1 : n1 + "_" + n2;
        if (cache.containsKey(key))
            return cache.get(key);
		int gcd = 0;
		if (n1 < n2) {
			n1 = n1 + n2;
			n2 = n1 - n2;
			n1 = n1 - n2;
		}
		if (n1 % n2 == 0) {
			gcd = n2;
		}
		while (n1 % n2 > 0) {
			n1 = n1 % n2;
			if (n1 < n2) {
				n1 = n1 + n2;
				n2 = n1 - n2;
				n1 = n1 - n2;
			}
			if (n1 % n2 == 0) {
				gcd = n2;
			}
		}
        cache.put(key, gcd);
		return gcd;
    }
}