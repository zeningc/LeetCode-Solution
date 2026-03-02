class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int base = nums[0];
        int pos = 0;
        while (true)    {
            int i = start;
            int j = end;
            while (i < j)  {
                while (i < j && nums[j] >= base)
                    j--;

                if (i < j)  {
                    nums[pos] = nums[j];
                    pos = j;
                }

                while (i < j && nums[i] <= base)
                    i++;
                
                if (i < j)  {
                    nums[pos] = nums[i];
                    pos = i;
                }

                nums[pos] = base;
            }

            if (nums.length - pos == k)
                return base;
            if (nums.length - pos < k)
                end = pos - 1;
            if (nums.length - pos > k)
                start = pos + 1;
            base = nums[start];
            pos = start;
        }

    }
}