class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, new Comparator<String>()  {
            @Override
            public int compare(String s1, String s2)    {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        
        if (strs[strs.length - 1].equals("0") && strs[0].equals("0"))
            return "0";
        
        StringBuilder sb = new StringBuilder();
        
        for (String str : strs)
            sb.append(str);
        
        return sb.toString();
    }
}
