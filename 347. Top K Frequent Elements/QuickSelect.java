class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        int[][] arr = new int[freq.size()][2];
        
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet())
            arr[idx++] = new int[] {e.getKey(), e.getValue()};
        
        int start = 0;
        int n = arr.length;
        int end = n - 1;
        int mark = -1;
        while (true)    {
            int pos = start;
            int pivot = arr[start][1];
            int[] pivotArr = arr[start];
            int i = start;
            int j = end;
            while (i < j)   {
                while (j > i && arr[j][1] >= pivot)
                    j--;
                if (j >= i) {
                    arr[pos] = arr[j];
                    pos = j;
                }
                while (i < j && arr[i][1] <= pivot)
                    i++;
                if (i <= j) {
                    arr[pos] = arr[i];
                    pos = i;
                }
            }
            
            arr[pos] = pivotArr;
            
            if (n - pos == k)   {
                mark = pos;
                break;
            }
            else if (n - pos < k)   {
                end = pos - 1;
            }
            else    {
                start = pos + 1;
            }
        }
        int[] ans = new int[k];
        for (int i = mark; i < mark + k; i++)
            ans[i - mark] = arr[i][0];
        
        return ans;
    }
}
