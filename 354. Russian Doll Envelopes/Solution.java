class Solution {
    int[][] lis;
    public int maxEnvelopes(int[][] envelopes) {
        int ans = 0;
        
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        lis = new int[envelopes.length][2];
        int len = 0;
        
        for (int i = 0; i < envelopes.length; i++)  {
            int w = envelopes[i][0];
            int h = envelopes[i][1];
            int left = 0;
            int right = len - 1;
            while (left <= right)   {
                int mid = left + (right - left) / 2;
                if (check(mid, w, h))
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            
            if (left == len)
                len++;
            
            lis[left] = envelopes[i];
        }
        
        return len;
    }
    
    boolean check(int mid, int w, int h)    {
        if (lis[mid][0] == w)
            return true;

        return lis[mid][1] >= h;
    }
}
