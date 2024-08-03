class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[][] pre0 = new int[n][32];
        for (int i = 0; i < n; i++)
            Arrays.fill(pre0[i], -1);
        long ans = 0;
        int[] last0 = new int[32];
        Arrays.fill(last0, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++)    {
                pre0[i][j] = last0[j];
                if ((nums[i] & (1 << j)) == 0)
                    last0[j] = i;
            }
        }
        
        
        for (int i = 0; i < n; i++) {
            List<int[]> list = new ArrayList<>();
            boolean invalid = false;
            for (int j = 0; j < 32; j++)    {
                int kBit = (k & (1 << j)) != 0 ? 1 : 0;
                int curBit = (nums[i] & (1 << j)) != 0 ? 1 : 0;
                if (kBit == 1 && curBit == 0)   {
                    invalid = true;
                    break;
                }
                if (kBit == 0 && curBit == 0)   {
                    list.add(new int[] {0, 1});
                    list.add(new int[] {i, -1});
                    continue;
                }
                if (kBit == 0 && curBit == 1)   {
                    if (pre0[i][j] == -1)    {
                        invalid = true;
                        break;
                    }
                    list.add(new int[] {0, 1});
                    list.add(new int[] {pre0[i][j], -1});
                    continue;
                }
                if (kBit == 1 && curBit == 1)   {
                    list.add(new int[] {pre0[i][j] + 1, 1});
                    list.add(new int[] {i, -1});
                    continue;
                }
            }
            if (invalid)
                continue;
            list.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            int cnt = 0;
            int start = -1;
            int end = -1;
            for (int[] cur : list)  {
                cnt += cur[1];
                if (cnt == 32 && start == -1)
                    start = cur[0];
                if (cnt == 31 && end == -1 && start != -1)
                    end = cur[0];
            }
            
            if (start != -1 && end != -1)
                ans += end - start + 1;
        }
        
        return ans;
    }
}