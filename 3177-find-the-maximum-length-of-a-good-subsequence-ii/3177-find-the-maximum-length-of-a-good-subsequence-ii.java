class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int[][] pre = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            pre[i][0] = -1;
            pre[i][1] = 0;
            pre[i][2] = -1;
            pre[i][3] = 0;
        }
        Map<Integer, Integer> maxMap = new HashMap<>();
        for (int x = 0; x <= k; x++)    {
            maxMap.clear();
            int[][] nxt = new int[n + 1][4];
            nxt[0][0] = -1;
            nxt[0][1] = 0;
            nxt[0][2] = -1;
            nxt[0][3] = 0;
            
            for (int y = 0; y < n; y++) {
                int cur = 0;
                if (x != 0)  {
                    if (pre[y][0] != nums[y])
                        cur = Math.max(cur, pre[y][1] + 1);
                    else if (pre[y][2] != nums[y])
                        cur = Math.max(cur, pre[y][3] + 1);
                }
                
                cur = Math.max(cur, maxMap.getOrDefault(nums[y], 0) + 1);
                
                nxt[y + 1][0] = nxt[y][0];
                nxt[y + 1][1] = nxt[y][1];
                nxt[y + 1][2] = nxt[y][2];
                nxt[y + 1][3] = nxt[y][3];
                
                if (nxt[y][0] == -1 || nxt[y][0] != -1 && nxt[y][1] <= cur) {
                    if (nxt[y][0] != -1 && nxt[y][1] <= cur) {
                        nxt[y + 1][2] = nxt[y][0];
                        nxt[y + 1][2] = nxt[y][1];
                    }
                    nxt[y + 1][0] = nums[y];
                    nxt[y + 1][1] = cur;
                }
                else if (nxt[y][2] == -1 || nxt[y][2] != -1 && nxt[y][3] <= cur)   {
                    nxt[y + 1][2] = nums[y];
                    nxt[y + 1][3] = cur;
                }
                
                maxMap.put(nums[y], Math.max(maxMap.getOrDefault(nums[y], 0), cur));
                ans = Math.max(ans, cur);
            }
            
            pre = nxt;
        }
        
        return ans;
    }
}