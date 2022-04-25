class Solution {
    int[] sweetness;
    int k;
    public int maximizeSweetness(int[] sweetness, int k) {
        this.sweetness = sweetness;
        this.k = k;
        int left = 1;
        int right = 0;
        for (int s : sweetness)
            right += s;
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (check(mid)) 
                left = mid + 1;
            else    
                right = mid - 1;
        }
        
        return right;
    }
    
    boolean check(int min)  {
        int cut = 0;
        int curr = 0;
        int lastCut = -1;
        for (int i = 0; i < sweetness.length; i++)  {
            curr += sweetness[i];
            if (curr >= min)    {
                cut++;
                curr = 0;
                lastCut = i;
            }
        }
        if (cut < k)
            return false;
        
        if (cut == k)   {
            curr = 0;
            for (int i = lastCut + 1; i < sweetness.length; i++)    {
                curr += sweetness[i];
            }
            return curr >= min;
        }
        
        return true;
        
    }
}
