class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        Map<Integer, List<int[]>> modMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> e : freq.entrySet())   {
            if (!modMap.containsKey(e.getKey() % k))
                modMap.put(e.getKey() % k, new ArrayList<>());
            modMap.get(e.getKey() % k).add(new int[] {e.getKey(), e.getValue()});
        }
        int ret = 1;
        int[] twos = new int[21];
        twos[0] = 1;
        for (int i = 1; i <= 20; i++)
            twos[i] = twos[i - 1] * 2;
        for (Map.Entry<Integer, List<int[]>> e : modMap.entrySet()) {
            List<int[]> list = e.getValue();
            Collections.sort(list, (a, b) -> a[0] - b[0]);
            int take = 0;
            int notTake = 1;
            for (int i = 0; i < list.size(); i++)   {
                int takeCopy = take;
                int notTakeCopy = notTake;
                if (i > 0 && list.get(i)[0] == list.get(i - 1)[0] + k)    {
                    take = notTakeCopy * (int)(twos[list.get(i)[1]] - 1);
                    
                }
                else    {
                    take = (takeCopy + notTakeCopy) * (int)(twos[list.get(i)[1]] - 1);
                }
                notTake = (takeCopy + notTakeCopy);
            }
            ret *= (take + notTake);
        }
        
        return ret - 1;
    }
}