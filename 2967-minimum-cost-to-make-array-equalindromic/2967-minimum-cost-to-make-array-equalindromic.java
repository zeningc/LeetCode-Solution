class Solution {
    public long minimumCost(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        long median = n % 2 == 0 ? (nums[n / 2] + nums[(n - 1) / 2]) / 2 : nums[n / 2];
        if (median < 10)
            return getCost(nums, median);
        if (median <= 16)
            return Math.min(getCost(nums, 9), getCost(nums, 11));
        if (median < 20)
            return getCost(nums, 22);
        
        String numStr = String.valueOf(median);
        String flipPartStr = numStr.substring(0, (numStr.length() + 1) / 2);
        long flipPart = Long.valueOf(flipPartStr);
        long a = flip(numStr.length(), flipPartStr);
        if (a == median)
            return getCost(nums, a);
        long b;
        if (a < median) {
            if (String.valueOf(flipPart + 1).length() > String.valueOf(flipPart).length())    {
                StringBuilder sb = new StringBuilder();
                sb.append('1');
                for (int i = 0; i < numStr.length() - 1; i++)
                    sb.append('0');
                sb.append('1');
                b = Long.valueOf(sb.toString());
            }
            else    {
                b = flip(numStr.length(), String.valueOf(flipPart + 1));
            }
        }
        else    {
            if (String.valueOf(flipPart - 1).length() < String.valueOf(flipPart).length())    {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numStr.length() - 1; i++)
                    sb.append('9');
                b = Long.valueOf(sb.toString());
            }
            else    {
                b = flip(numStr.length(), String.valueOf(flipPart - 1));
            }
        }
        
        return Math.min(getCost(nums, a), getCost(nums, b));
    }
    
    long flip(int len, String str)  {
        if (len % 2 == 0)   {
            return Long.valueOf(str + new StringBuilder(str).reverse().toString());
        }
        return Long.valueOf(str + new StringBuilder(str.substring(0, str.length() - 1)).reverse().toString());
    }
    
    long getCost(int[] nums, long target)    {
        long sum = 0;
        for (int num : nums)
            sum += Math.abs(target - num);
        return sum;
    }
}
