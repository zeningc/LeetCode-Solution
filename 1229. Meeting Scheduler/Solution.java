class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        
        int p = 0;
        int q = 0;
        
        while (p < slots1.length && q < slots2.length)  {
            if (slots1[p][1] <= slots2[q][0])    {
                p++;
            }
            else if (slots1[p][0] >= slots2[q][1])   {
                q++;
            }
            else    {
                int start = Math.max(slots1[p][0], slots2[q][0]);
                int end = Math.min(slots1[p][1], slots2[q][1]);
                if (start + duration <= end)    {
                    List<Integer> ans = new LinkedList<>();
                    ans.add(start);
                    ans.add(start + duration);
                    return ans;
                }
                
                if (slots1[p][1] <= slots2[q][1])
                    p++;
                else
                    q++;
            }
        }
        
        return new LinkedList<>();
    }
}
