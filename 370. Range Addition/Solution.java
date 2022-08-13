class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] delta = new int[length + 1];
        for (int[] update : updates)  {
            delta[update[0]] += update[2];
            delta[update[1] + 1] -= update[2];
        }
        int[] ans = new int[length];
        int sum = 0;
        for (int i = 0; i < length; i++)    {
            sum += delta[i];
            ans[i] += sum;
        }
        return ans;
    }
}