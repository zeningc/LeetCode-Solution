class Solution {
    public String robotWithString(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int n = s.length();
        CharStat stat = new CharStat(s);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++)
        {
            while (!stack.isEmpty() && s.charAt(stack.peek()) <= s.charAt(i) && !stat.hasSmaller(s.charAt(stack.peek())))
            {
                // debug(s, stack);
                int pop = stack.pop();
                sb.append(s.charAt(pop));
            }
            
            stack.push(i);
            stat.consumeChar(s.charAt(i));
            // debug(s, stack);
        }
        
        while (!stack.isEmpty())
        {
            sb.append(s.charAt(stack.pop()));
        }
        
        return sb.toString();
    }
    
    void debug(String s, Deque<Integer> stack)
    {
        for (int num : stack)
        {
            System.out.print(s.charAt(num));
        }
        System.out.println();
    }
    
}

class CharStat
{
    
    int[] freq;
    int p;
    
    public CharStat(String s)
    {
        freq = new int[26];
        p = 0;
        for (char c : s.toCharArray())
        {
            freq[c - 'a']++;
        }
        updatePointer();
    }
    
    void initPointer()
    {
        p = 0;
    }
    
    void updatePointer()
    {
        while (p < 26)
        {
            if (freq[p] != 0)
                break;
            p++;
        }
    }
    
    void consumeChar(char c)
    {
        freq[c - 'a']--;
        updatePointer();
    }
    
    void addChar(char c)
    {
        freq[c - 'a']++;
        initPointer();
        updatePointer();
    }
    
    boolean hasSmaller(char c)
    {
        return p < c - 'a';
    }
}