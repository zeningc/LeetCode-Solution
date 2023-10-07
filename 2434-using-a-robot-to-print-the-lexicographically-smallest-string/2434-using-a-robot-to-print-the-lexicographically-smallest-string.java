class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        Deque<Character> stack = new LinkedList<>();
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray())  {
            
            while (!stack.isEmpty() && !hasSmaller(freq, stack.peek()))    {
                ans.append(stack.pop());
            }
            freq[c - 'a']--;
            stack.push(c);
        }
        while (!stack.isEmpty())    {
            ans.append(stack.pop());
        }
        return ans.toString();
    }
    
    
    boolean hasSmaller(int[] freq, char c)  {
        for (int i = 0; i < c - 'a'; i++)
            if (freq[i] != 0)
                return true;
        return false;
    }
}