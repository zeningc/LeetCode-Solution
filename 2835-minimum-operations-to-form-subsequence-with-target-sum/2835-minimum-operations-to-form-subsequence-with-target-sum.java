class Solution {
    public int minOperations(List<Integer> nums, int target) {
        Map<Integer, Integer> numToPow = new HashMap<>();

        for (int i = 0, j = 1; i < 31; i++) {
            numToPow.put(j, i);
            j *= 2;
        }
        

        int[] bin = new int[32];
        int ans = 0;
        long sum = 0;
        for (int num : nums)    {
            sum += num;
            bin[numToPow.get(num)]++;
        }
        if (sum < (long)target)
            return -1;
        
        for (int i = 0; i < 32; i++)    {
            if ((target & (1 << i)) != 0)   {
                for (int j = 0; j < i; j++) {
                    bin[j + 1] += bin[j] / 2;
                    bin[j] %= 2;
                }
                
                if (bin[i] == 0)    {
                    for (int j = i + 1; j < 32; j++)    {
                        if (bin[j] != 0)    {
                            for (int k = j - 1; k >= i; k--)    {
                                bin[k + 1] -= 1;
                                bin[k] += 2;
                                ans++;
                            }
                            break;
                        }
                    }
                }
                
                bin[i]--;
            }
        }
        
        return ans;
    }
    
    
}