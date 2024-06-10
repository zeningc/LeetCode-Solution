class Solution {
    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int num : nums)
            m.computeIfAbsent(num % k, x -> new ArrayList<>()).add(num);
        long ret = 1;
        for (Map.Entry<Integer, List<Integer>> e : m.entrySet())  {
            List<Integer> l = e.getValue();
            int preKey = -1;
            Collections.sort(l);
            long preTake = 0;
            long preNotTake = 1;
            for (int num : l)   {
                long notTake = preTake + preNotTake;
                long take = 0;
                if (preKey + k == num)  {
                    take = preNotTake;
                }
                else    {
                    take = preNotTake + preTake;
                }
                preTake = take;
                preNotTake = notTake;
                preKey = num;
            }
            ret *= (preTake + preNotTake);
        }
        
        return ret;
    }
}