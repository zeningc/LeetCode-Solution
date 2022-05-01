class Solution {
    public String removeDigit(String number, char digit) {
        int n = number.length();
        Queue<String> pq = new PriorityQueue<>((a, b) -> compareString(a, b));
        for (int i = n - 1; i > -1; i--)    {
            if (number.charAt(i) == digit)
                pq.offer(number.substring(0, i) + number.substring(i + 1, n));
        }
        
        return pq.poll();
    }
    
    int compareString(String a, String b) {
        if (a.length() != b.length())   {
            return b.length() - a.length();
        }
        
        for (int i = 0; i < a.length(); i++)    {
            if (a.charAt(i) != b.charAt(i)) {
                return (int)(b.charAt(i) - a.charAt(i));
            }
        }
        
        return 0;
    }
}
