class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int cnt1 = 0;
        int cnt2 = 0;
        int x1 = 0;
        int x2 = 0;
        for (int num : nums)    {
            if (cnt1 > 0 && num == x1)  {
                cnt1++;
                continue;
            }
            
            if (cnt2 > 0 && num == x2)  {
                cnt2++;
                continue;
            }
            
            if (cnt1 == 0)  {
                x1 = num;
                cnt1++;
                continue;
            }
            
            if (cnt2 == 0)  {
                x2 = num;
                cnt2++;
                continue;
            }
            
            cnt1--;
            cnt2--;
        }
        
        int freq1 = 0;
        int freq2 = 0;
        for (int num : nums)    {
            if (cnt1 > 0 && num == x1)
                freq1++;
            if (cnt2 > 0 && num == x2)
                freq2++;
        }
        
        List<Integer> ans = new ArrayList<>();
        if (freq1 > nums.length / 3)
            ans.add(x1);
        if (freq2 > nums.length / 3)
            ans.add(x2);
        
        return ans;
    }
}