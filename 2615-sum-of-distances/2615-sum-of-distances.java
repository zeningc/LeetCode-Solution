class Solution {
    public long[] distance(int[] arr) {
        Map<Integer, Long> m = new HashMap<>();
        Map<Integer, Long> freq = new HashMap<>();
        long[] ans = new long[arr.length];
        for (int i = 0; i < arr.length; i++)    {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0L) + 1);
            m.put(arr[i], m.getOrDefault(arr[i], 0L) + i);
            ans[i] += freq.get(arr[i]) * i - m.get(arr[i]);
        }
        freq = new HashMap<>();
        m = new HashMap<>();
        for (int i = arr.length - 1; i >= 0; i--)   {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0L) + 1);
            m.put(arr[i], m.getOrDefault(arr[i], 0L) + i);
            ans[i] += m.get(arr[i]) - freq.get(arr[i]) * i;
        }
        return ans;
    }
}