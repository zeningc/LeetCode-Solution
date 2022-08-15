class Solution {
    public String smallestNumber(String pattern) {
        StringBuilder ans = new StringBuilder();
        StringBuilder stack = new StringBuilder();
        char num = '1';
        int n = pattern.length();
        for (int i = 0; i <= n; i++)  {
            stack.append(num++);
            if (i == n || pattern.charAt(i) == 'I') {
                ans.append(stack.reverse());
                stack = new StringBuilder();
            }
        }
        
        return ans.toString();
    }
}
