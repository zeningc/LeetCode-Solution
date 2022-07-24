class Solution {
    public boolean isValid(String s) {
        Deque<Character> q = new LinkedList<>();
        for (char c : s.toCharArray())  {
            if (c == '(' || c == '{' || c == '[')
                q.push(c);
            else if (c == ')'){
                if (q.isEmpty() || q.pop() != '(')
                    return false;}
            else if (c == '}'){
                if (q.isEmpty() || q.pop() != '{')
                    return false;}
            else if (c == ']'){
                if (q.isEmpty() || q.pop() != '[')
                    return false;}
        }
        
        return q.isEmpty();
    }
}