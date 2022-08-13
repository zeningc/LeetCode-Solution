class Solution {
    int ans = 0;
    List<String> arr;
    Map<String, Integer> maskMap;
    public int maxLength(List<String> arr) {
        this.arr = arr;
        maskMap = new HashMap<>();
        for (String str : arr)  {
            int mask = 0;
            boolean flag = true;
            for (char c : str.toCharArray())  {
                if ((mask & (1 << (c - 'a'))) != 0) {
                    flag = false;
                    break;
                }
                mask |= (1 << (c - 'a'));
            }
            if (flag)
                maskMap.put(str, mask);
        }
        
        dfs(0, 0);
        
        return ans;
    }
    
    void dfs(int mask, int idx) {
        if (idx >= arr.size())  {
            int cnt = 0;
            while (mask != 0)   {
                mask = mask & (mask - 1);
                cnt++;
            }
            ans = Math.max(ans, cnt);
            return;
        }
        
        String s = arr.get(idx);
        
        if (maskMap.containsKey(s) && (mask & (maskMap.get(s))) == 0) {
            dfs(mask | (maskMap.get(s)), idx + 1);
        }
        
        dfs(mask, idx + 1);
    }
}
