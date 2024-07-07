class Solution {
    public int findValidSplit(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++)   {
            int t = nums[i];
            for (int j = 2; j * j <= nums[i]; j++)  {
                if (t % j != 0)
                    continue;
                if (!first.containsKey(j))
                    first.put(j, i);
                last.put(j, i);
                while (t % j == 0)  {
                    t /= j;
                }
            }
            if (t != 1) {
                if (!first.containsKey(t))
                    first.put(t, i);
                last.put(t, i);
            }
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int key : first.keySet())  {
            int firstVal = first.get(key);
            int lastVal = last.get(key);
            m.put(firstVal, m.getOrDefault(firstVal, 0) + 1);
            m.put(lastVal, m.getOrDefault(lastVal, 0) - 1);
        }
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++)   {
            cnt += m.getOrDefault(i, 0);
            if (cnt == 0)
                return i;
        }
        
        return -1;
    }
}
