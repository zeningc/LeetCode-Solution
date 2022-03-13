class Solution {
    public int maximumTop(int[] nums, int k) {
        int n = nums.length;
        if (k == 0)
            return nums[0];
        if (n == 1) {
            if (k % 2 == 0)
                return nums[0];
            return -1;
        }
        if (k == 1) {
            return nums[1];
        }
        int range = 0;
        if (k <= n) {
            range = k - 1;
        } else {
            range = n;
        }

        int[] arr = new int[range];
        for (int i = 0; i < range; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort(arr);
        if (k < n) {
            return Math.max(nums[k], arr[range - 1]);
        }
        return arr[range - 1];
    }
}
