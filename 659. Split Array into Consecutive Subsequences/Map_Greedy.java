class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> seq = new HashMap<>();
        
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        for (int num : nums)    {
            
            if (freq.get(num) == 0)
                continue;
            
            if (seq.getOrDefault(num - 1, 0) > 0)   {
                seq.put(num - 1, seq.getOrDefault(num - 1, 0) - 1);
                seq.put(num, seq.getOrDefault(num, 0) + 1);
            }
            else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0)    {
                freq.put(num + 1, freq.getOrDefault(num + 1, 0) - 1);
                freq.put(num + 2, freq.getOrDefault(num + 2, 0) - 1);
                seq.put(num + 2, seq.getOrDefault(num + 2, 0) + 1);
            }
            else    {
                return false;
            }
            
            freq.put(num, freq.getOrDefault(num, 0) - 1);
        }
        
        return true;
    }
}
