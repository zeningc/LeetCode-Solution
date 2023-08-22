class Solution {
    public long[] getDistances(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Long> sum = new HashMap<>();
        long[] ans = new long[arr.length];
        for (int i = 0; i < arr.length; i++)    {
            int f = freq.getOrDefault(arr[i], 0);
            ans[i] += (long)f * i - sum.getOrDefault(arr[i], 0L);
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
            sum.put(arr[i], sum.getOrDefault(arr[i], 0L) + i);
        }
        freq = new HashMap<>();
        sum = new HashMap<>();
        for (int i = arr.length - 1; i >= 0; i--)    {
            int f = freq.getOrDefault(arr[i], 0);
            ans[i] += sum.getOrDefault(arr[i], 0L) - (long)f * i;
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
            sum.put(arr[i], sum.getOrDefault(arr[i], 0L) + i);
        }
        return ans;
    }
}