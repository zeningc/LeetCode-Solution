class Solution {
    public int[] findPermutation(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int cnt = 1;
        int p = 0;
        int[] ans = new int[s.length() + 1];
        for (int i = 0; i <= s.length(); i++)   {
            stack.push(cnt++);
            if (i == s.length() || s.charAt(i) == 'I')  {
                while (!stack.isEmpty())    {
                    ans[p++] = stack.pop();
                }
            }
        }
        return ans;
    }
}
