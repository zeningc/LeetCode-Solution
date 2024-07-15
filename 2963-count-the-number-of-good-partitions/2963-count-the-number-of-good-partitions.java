class Solution {
    public int numberOfGoodPartitions(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> maxMap = new HashMap<>();
        Map<Integer, Integer> minMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!minMap.containsKey(nums[i]))
                minMap.put(nums[i], i);
            maxMap.put(nums[i], i);
            set.add(nums[i]);
        }
        
        List<int[]> list = new ArrayList<>();
        for (int num : set) {
            int max = maxMap.get(num);
            int min = minMap.get(num);
            list.add(new int[] {min, 1});
            list.add(new int[] {max, -1});
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int cnt = 0;
        int groupCnt = 0;
        for (int[] curr : list) {
            cnt += curr[1];
            
            if (curr[1] == -1 && cnt == 0)
                groupCnt++;
        }
        
        long ans = 1L;
        int mod = (int)1e9 + 7;
        for (int i = 1; i < groupCnt; i++) {
            ans = (ans * 2) % mod;
        }
        return (int)(ans % mod);
    }
}