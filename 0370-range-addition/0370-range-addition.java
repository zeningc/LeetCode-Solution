class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] delta = new int[length + 1];
        for (int[] update : updates)    {
            int start = update[0];
            int end = update[1] + 1;
            int d = update[2];
            delta[start] += d;
            delta[end] -= d;
        }
        
        int[] ans = new int[length];
        int add = 0;
        for (int i = 0; i < length; i++)    {
            add += delta[i];
            ans[i] = add;
        }
        
        return ans;
    }
}

