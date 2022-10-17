class Solution {
    
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int m = nums.size();
        int cnt = 0;
        int n = 0;
        for (int i = 0; i < m; i++) {
            cnt += nums.get(i).size();
            n = Math.max(n, nums.get(i).size());
        }
        int p = 0;
        int[] ans = new int[cnt];
        
        for (int i = 0; i < m; i++) {
            int x = i;
            int y = 0;
            while (x >= 0)    {
                if (y < nums.get(x).size())
                    ans[p++] = nums.get(x).get(y);
                x--;
                y++;
            }
        }
        
        for (int j = 1; j < n; j++) {
            int y = j;
            int x = m - 1;
            while (x >= 0 && y < n)   {
                if (y < nums.get(x).size())
                    ans[p++] = nums.get(x).get(y);
                x--;
                y++;
            }
        }
        
        return ans;
    }
}