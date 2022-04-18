class Solution {
    public String minimizeResult(String expression) {
        int plusIdx = -1;
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == '+')   {
                plusIdx = i;
                break;
            }
        }
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        for (int i = 0; i < plusIdx; i++)   {
            for (int j = plusIdx + 1; j < n; j++) {
                int leftM = i == 0 ? 1 : Integer.parseInt(expression.substring(0, i));
                int rightM = j == n - 1 ? 1 : Integer.parseInt(expression.substring(j + 1, n));
                int leftA = Integer.parseInt(expression.substring(i, plusIdx));
                int rightA = Integer.parseInt(expression.substring(plusIdx + 1, j + 1));
                int val = (leftA + rightA) * leftM * rightM;
                if (val < min)  {
                    left = i;
                    right = j;
                    min = val;
                }
            }
        }
        
        return expression.substring(0, left) + "(" + expression.substring(left, right + 1) + ")" + expression.substring(right + 1, n);
    }
}