class Solution {
    public long makeSubKSumEqual(int[] arr, int k) {
        long ans = 0;
        boolean[] vis = new boolean[arr.length];
        for (int i = 0; i < Math.min(arr.length, k); i++)    {
            if (vis[i])
                continue;
            List<Integer> nums = new ArrayList<>(arr.length);
            int j = i;
            long sum = 0;
            while (!vis[j]) {
                vis[j] = true;
                nums.add(arr[j]);
                sum += arr[j];
                j = (j + k) % arr.length;
            }
            Collections.sort(nums);
            int mid = nums.get(nums.size() / 2);
            long operationCnt = 0;
            for (int num : nums)
                operationCnt += Math.abs(num - mid);
            if (nums.size() % 2 == 0)   {
                long t = 0;
                for (int num : nums)
                    t += Math.abs(num - nums.get(nums.size() / 2 - 1));
                operationCnt = Math.min(operationCnt, t);
            }
            ans += operationCnt;
        }
        
        return ans;
    }
}