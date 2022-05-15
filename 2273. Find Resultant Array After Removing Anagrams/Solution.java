class Solution {
    int[][] freq;
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        freq = new int[n][26];
        for (int i = 0; i < n; i++)    {
            String word = words[i];
            for (char c : word.toCharArray())   {
                freq[i][c - 'a']++;
            }
        }
        
        Deque<Integer> stack = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (!stack.isEmpty() && check(i, stack.peek()))
                continue;
            stack.push(i);
        }
        
        List<String> ans = new LinkedList<>();
        
        for (int item : stack)
            ans.add(0, words[item]);
        
        return ans;
    }
    
    boolean check(int i, int j) {
        for (int x = 0; x < 26; x++)    {
            if (freq[i][x] != freq[j][x])
                return false;
        }
        return true;
    }
}
