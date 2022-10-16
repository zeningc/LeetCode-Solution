class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        if (minK > maxK)
            return 0l;
        int lastBreak = -1;
        int n = nums.length;
        long ans = 0;
        if (minK == maxK)   {
            int idx = 0;
            while (idx < n) {
                if (nums[idx] != minK)  {
                    lastBreak = idx;
                    idx++;
                    continue;
                }
                int j = idx + 1;
                while (j < n && nums[idx] == nums[j])
                    j++;
                if (j == idx + 1){
                    ans++;
                }
                else    {
                   ans += comb(j - idx, 2) + j - idx; 
                }
                idx = j;
            }
        }
        Deque<Integer> q1 = new LinkedList<>();
        Deque<Integer> q2 = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK)   {
                q1.clear();
                q2.clear();
                lastBreak = i;
                continue;
            }
            if (nums[i] == minK)    {
                q1.offerLast(i);
                if (!q2.isEmpty())  {
                    ans += (q2.peekLast() - lastBreak);
                }
            }
            else if (nums[i] == maxK)    {
                q2.offerLast(i);
                if (!q1.isEmpty())  {
                    ans += (q1.peekLast() - lastBreak);
                }
            }
            else    {
                if (!q1.isEmpty() && !q2.isEmpty()) {
                    ans += Math.min(q1.peekLast(), q2.peekLast()) - lastBreak;
                }
            }
            // System.out.println(nums[i] + ": " + ans + " " + lastBreak);
        }
        
        return ans;
    }
    
    long comb(int m,int n)  {
        if(n==0)
            return 1;
        if (n==1) 
            return m;
        if(n>m/2)
            return comb(m,m-n);
        if(n>1)
            return comb(m-1,n-1)+comb(m-1,n);  
        return -1;
    }

}