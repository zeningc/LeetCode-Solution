class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new LinkedList<>();
        int i = 0; 
        int n = s.length();
        while (i < n)   {
            int j = i;
            
            while (j < n && s.charAt(j) == s.charAt(i))
                j++;
            
            int freq = j - i;
            if (!stack.isEmpty() && stack.peek().c == s.charAt(i))
                freq += stack.pop().freq;
            
            if (freq >= k)
                freq %= k;
            if (freq != 0)
                stack.push(new Pair(s.charAt(i), freq));
            
            i = j;
        }
        
        StringBuilder sb = new StringBuilder();
        for (Pair pair : stack)
            for (int j = 0; j < pair.freq; j++)
                sb.append(pair.c);
        
        return sb.reverse().toString();
    }
}

class Pair  {
    char c;
    int freq;
    
    public Pair(char c, int freq)   {
        this.c = c;
        this.freq = freq;
    }
}
