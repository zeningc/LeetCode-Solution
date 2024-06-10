class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, Integer> f = new HashMap<>();
        Map<Integer, List<int[]>> m = new HashMap<>();
        for (int num : nums)
            f.put(num, f.getOrDefault(num, 0) + 1);
        for (Map.Entry<Integer, Integer> e : f.entrySet())  {
            int key = e.getKey();
            int val = e.getValue();
            m.computeIfAbsent(key % k, x -> new ArrayList<>()).add(new int[] {key, val});
        }
        int[] twos = new int[21];
        twos[0] = 1;
        for (int i = 1; i <= 20; i++)
            twos[i] = twos[i - 1] * 2;
        int ret = 1;
        for (Map.Entry<Integer, List<int[]>> e : m.entrySet())    {
            List<int[]> l = e.getValue();
            Collections.sort(l, (a, b) -> a[0] - b[0]);
            int preKey = -1;
            int preTake = 0;
            int preNotTake = 1;
            for (int[] cur : l) {
                int key = cur[0];
                int val = cur[1];
                int notTake = (preTake + preNotTake);
                int take = 0;
                if (key - k == preKey)  {
                    take = preNotTake * (twos[val] - 1);
                }
                else    {
                    take = (preTake + preNotTake) * (twos[val] - 1);
                }
                preTake = take;
                preNotTake = notTake;
                preKey = key;
            }
            ret *= (preTake +preNotTake);
        }
        return ret - 1;
    }
}