class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        boolean[] vis = new boolean[26];
        int[] cnt = new int[26];
        for (char c : s.toCharArray())
            cnt[c - 'a']++;
        Deque<Character> stack = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (!vis[c - 'a'] && !stack.isEmpty() && c < stack.peek() && cnt[stack.peek() - 'a'] > 0)  {
                char ch = stack.pop();
                vis[ch - 'a'] = false;           
            }
            cnt[c - 'a']--;
            if (!vis[c - 'a'])    {
                vis[c - 'a'] = true;
                stack.push(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        
        return sb.reverse().toString();
    }
}