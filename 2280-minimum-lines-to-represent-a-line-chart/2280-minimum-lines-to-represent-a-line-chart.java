class Solution {
    public int minimumLines(int[][] stockPrices) {
        Arrays.sort(stockPrices, (a, b) -> a[0] - b[0]);
        int n = stockPrices.length;
        int ans = 0;
        int startX = stockPrices[0][0];
        int startY = stockPrices[0][1];
        int prevX = stockPrices[0][0];
        int prevY = stockPrices[0][1];
        for (int i = 1; i < n; i++) {
            int x = stockPrices[i][0];
            int y = stockPrices[i][1];
            if (prevX == startX && prevY == startY)    {
                ans++;
                prevX = x;
                prevY = y;
                continue;
            }
            
            if ((prevY - startY) * (x - prevX) == (y - prevY) * (prevX - startX))   {
                prevX = x;
                prevY = y;
                continue;
            }
            
            startX = prevX;
            startY = prevY;
            prevX = x;
            prevY = y;
            ans++;
        }
        
        return ans;
    }
}