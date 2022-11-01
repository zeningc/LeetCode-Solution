class Solution {
    List<String> ans;
    public List<String> restoreIpAddresses(String s) {
        ans = new LinkedList<>();
        dfs(s, new StringBuilder(), 0, 0, false, 3);
        return ans;
    }
    
    void dfs(String s, StringBuilder sb, int index, int num, boolean meet, int left) {
        if (index >= s.length())    {
            if (!meet)
                return;
            if (left != 0)
                return;
            String numStr = String.valueOf(num);
            sb.append(numStr);
            ans.add(sb.toString());
            sb.delete(sb.length() - numStr.length(), sb.length());
            return;
        }
        char c = s.charAt(index);
        if (meet && num == 0)
            return;
        num = num * 10 + c - '0';
        if (num > 255)
            return;
        if (left > 0)   {
            String numStr = String.valueOf(num);
            sb.append(numStr);
            sb.append('.');
            dfs(s, sb, index + 1, 0, false, left - 1);
            sb.delete(sb.length() - numStr.length() - 1, sb.length());
        }
        
        dfs(s, sb, index + 1, num, true, left);
    }
    
}