class Solution {
    public int squareFreeSubsets(int[] nums) {
        int n = nums.length;
        int mod = (int)1e9 + 7;
        Map<Integer, Integer> numToState = new HashMap<>();
        for (int i = 2; i <= 30; i++)   {
            int state = 0;
            int num = i;
            boolean valid = true;
            for (int j = 2; j <= 30 && num != 1; j++)   {
                int cnt = 0;
                while (num % j == 0)   {
                    cnt++;
                    state ^= (1 << j);
                    num /= j;
                }
                if (cnt > 1)    {
                    valid = false;
                    break;
                }
                    
            }
            if (valid)
                numToState.put(i, state);
        }
        
        int cntOfOne = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1)
                cntOfOne++;
        long power = 1;
        for (int i = 0; i < cntOfOne; i++)
            power = (power * 2) % mod;
        
        
        Map<Integer, Long> stateCnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1)
                continue;
            
            if (!numToState.containsKey(nums[i]))
                continue;
            
            Map<Integer, Long> cache = new HashMap<>();
            int curState = numToState.get(nums[i]);
            for (Map.Entry<Integer, Long> entry : stateCnt.entrySet())    {
                int preState = entry.getKey();
                long preStateCnt = entry.getValue();
                boolean valid = true;
                for (int j = 0; j < 30; j++)    {
                    int curBit = (curState & (1 << j)) != 0 ? 1 : 0;
                    int preBit = (preState & (1 << j)) != 0 ? 1 : 0;
                    if (curBit == 1 && preBit == 1) {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    cache.put(preState + curState, (cache.getOrDefault(preState + curState, 0L) + preStateCnt) % mod);
            }
            stateCnt.put(curState, (stateCnt.getOrDefault(curState, 0L) + 1) % mod);
            for (Map.Entry<Integer, Long> entry : cache.entrySet())
                stateCnt.put(entry.getKey(), (stateCnt.getOrDefault(entry.getKey(), 0L) + entry.getValue()) % mod);
        }
        
        
        long ans = (power + mod - 1) % mod;
        
        for (long val : stateCnt.values())
            ans = (ans + power * val) % mod;

        return (int)ans;
        
    }
    
    
}