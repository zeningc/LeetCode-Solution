class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new LinkedList<>();
        dfs(new StringBuilder(), 0, 0, n);
        return ans;
    }
    
    void dfs(StringBuilder sb, int left, int cnt, int n)    {
        if (cnt == 2 * n)   {
            if (left == 0)  {
                ans.add(sb.toString());
            }
            return;
        }
        
        sb.append('(');
        dfs(sb, left + 1, cnt + 1, n);
        sb.deleteCharAt(sb.length() - 1);
        
        if (left > 0)   {
            sb.append(')');
            dfs(sb, left - 1, cnt + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
