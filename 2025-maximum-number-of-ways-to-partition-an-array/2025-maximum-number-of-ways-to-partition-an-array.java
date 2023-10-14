class Solution {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        long total = 0L;
        for (int i = 0; i < n; i++)
        {
            presum[i + 1] = presum[i] + nums[i];
            total += nums[i];
        }
        Map<Long, List<Integer>> diffCnt = new HashMap<>();
        Map<Long, List<Integer>> diffToAdd = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < n - 1; i++)
        {
            diffCnt.computeIfAbsent((long)k - nums[i], x -> new ArrayList<Integer>()).add(i);
            long left = presum[i + 1];
            long right = total - left;
            if (left == right)
                ans++;
            long gap = right - left;
            if (diffCnt.containsKey(gap))
            {
                diffToAdd.computeIfAbsent(gap, x -> new ArrayList<Integer>()).add(i);
            }
        }
        
        Map<Integer, Integer> cnt = new HashMap<>();
        for (Map.Entry<Long, List<Integer>> e : diffToAdd.entrySet())
        {
            long gap = e.getKey();
            List<Integer> addList = e.getValue();
            if (!diffCnt.containsKey(gap))
                continue;
            List<Integer> diffList = diffCnt.get(gap);
            int q = 0;
            int add = addList.size();
            for (int pIdx : addList)
            {
                while (q < diffList.size() && diffList.get(q) <= pIdx)
                {
                    int qIdx = diffList.get(q);
                    cnt.put(qIdx, cnt.getOrDefault(qIdx, 0) + add);
                    q++;
                }
                add--;
            }
        }
        
        diffCnt.clear();
        diffToAdd.clear();
        for (int i = n - 1; i > 0; i--)
        {
            diffCnt.computeIfAbsent((long)k - nums[i], x -> new ArrayList<Integer>()).add(i);
            long left = presum[i];
            long right = total - left;
            long gap = left - right;
            if (diffCnt.containsKey(gap))
            {
                diffToAdd.computeIfAbsent(gap, x -> new ArrayList<Integer>()).add(i);
            }
        }
        
        for (Map.Entry<Long, List<Integer>> e : diffToAdd.entrySet())
        {
            long gap = e.getKey();
            List<Integer> addList = e.getValue();
            if (!diffCnt.containsKey(gap))
                continue;
            List<Integer> diffList = diffCnt.get(gap);
            int q = 0;
            int add = addList.size();
            for (int pIdx : addList)
            {
                while (q < diffList.size() && diffList.get(q) >= pIdx)
                {
                    int qIdx = diffList.get(q);
                    cnt.put(qIdx, cnt.getOrDefault(qIdx, 0) + add);
                    q++;
                }
                add--;
            }
        }
        
        for (Map.Entry<Integer, Integer> e : cnt.entrySet())
        {
            ans = Math.max(e.getValue(), ans);
        }
        
        return ans;
    }
}