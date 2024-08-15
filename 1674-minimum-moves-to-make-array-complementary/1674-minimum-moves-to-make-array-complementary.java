class Solution {
    public int minMoves(int[] nums, int limit) {
        List<int[]> list = new ArrayList<>();
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            map.put(2, map.getOrDefault(2, 0) + 2);
            map.put(Math.min(a, b) + 1, map.getOrDefault(Math.min(a, b) + 1, 0) - 2);
            map.put(Math.min(a, b) + 1, map.getOrDefault(Math.min(a, b) + 1, 0) + 1);
            map.put(a + b, map.getOrDefault(a + b, 0) - 1);
            map.put(a + b + 1, map.getOrDefault(a + b + 1, 0) + 1);
            map.put(Math.max(a, b) + limit + 1, map.getOrDefault(Math.max(a, b) + limit + 1, 0) -1);
            map.put(Math.max(a, b) + limit + 1, map.getOrDefault(Math.max(a, b) + limit + 1, 0) + 2);
            map.put(2 * limit + 1, map.getOrDefault(2 * limit + 1, 0) - 2);
        }
        

        int ans = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 2; i <= 2 * limit; i++)    {
            cnt += map.getOrDefault(i, 0);
            ans = Math.min(ans, cnt);
        }
        
        return ans;
    }
}

/*
2
min(a, b)

min(a, b) + 1
max(a, b) + limit;

max(a, b) + limit + 1;

2 * limit


*/