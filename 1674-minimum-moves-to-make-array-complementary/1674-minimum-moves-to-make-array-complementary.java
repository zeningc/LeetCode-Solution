class Solution {
    public int minMoves(int[] nums, int limit) {
        List<int[]> list = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            list.add(new int[] {2, 2});
            list.add(new int[] {Math.min(a, b) + 1, -2});
            list.add(new int[] {Math.min(a, b) + 1, 1});
            list.add(new int[] {a + b, -1});
            list.add(new int[] {a + b + 1, 1});
            list.add(new int[] {Math.max(a, b) + limit + 1, -1});
            list.add(new int[] {Math.max(a, b) + limit + 1, 2});
            list.add(new int[] {2 * limit + 1, -2});
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int cnt = 0;
        int ans = Integer.MAX_VALUE;
        int pre = -1;
        
        int i = 0;
        while (i < list.size())   {
            int j = i;
            if (list.get(j)[0] == 2 * limit + 1)
                break;
            while (j < list.size() && list.get(j)[0] == list.get(i)[0]) {
                cnt += list.get(j)[1];
                j++;
            }
            i = j;
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