class Solution {
    public String decodeString(String s) {
        Deque<Integer> cntStack = new LinkedList<>();
        Deque<StringBuilder> strStack = new LinkedList<>();
        int wordCnt = 0;
        StringBuilder word = new StringBuilder();
        for (char c : s.toCharArray())  {
            if (c >= '0' && c <= '9')   {
                wordCnt = wordCnt * 10 + c - '0';
            }
            else if (c == '[')  {
                cntStack.push(wordCnt);
                strStack.push(word);
                word = new StringBuilder();
                wordCnt = 0;
            }
            else if (c == ']')  {
                StringBuilder prev = strStack.pop();
                int cnt = cntStack.pop();
                for (int i = 0; i < cnt; i++)
                    prev.append(word);
                word = prev;
            }
            else    {
                word.append(c);
            }
        }
        
        return word.toString();
    }
}
