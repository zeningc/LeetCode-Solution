class Solution {
    String ans;
    String pattern;
    public String smallestNumber(String pattern) {
        this.pattern = pattern;
        dfs(new boolean[10], new StringBuilder(), 0);
        return ans;
    }
    
    void dfs(boolean[] vis, StringBuilder sb, int index) {
        if (ans != null)
            return;
        if (index > pattern.length())   {
            ans = sb.toString();
            return;
        }
        char lo = '1';
        char hi = '9';
        if (sb.length() != 0)   {
            char c = sb.charAt(sb.length() - 1);
            if (pattern.charAt(index - 1) == 'I')   {
                lo = (char)((int)c + 1);
            }
            else    {
                hi = (char)((int)c - 1);
            }
        }
        
        for (char c = lo; c <= hi; c++) {
            if (vis[c - '0'])
                continue;
            sb.append(c);
            vis[c - '0'] = true;
            dfs(vis, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
            vis[c - '0'] = false;
        }
    }
}
