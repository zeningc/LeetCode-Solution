class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int ans = 0;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }
        while (true) {
            int flag = 0;
            for (int i = 0; i + 1 < n; i += 2) {
                if (row[i] + 1 != row[i + 1] && row[i] != row[i + 1] + 1) {
                    flag = 1;
                    ans += process(row, pos, i);
                }
            }
            if (flag == 0)
                break;
        }
        return ans;
    }

    int process(int[] arr, int[] pos, int i) {
        int ans = 0;
        while (true) {
            int find = arr[i] % 2 == 0 ? arr[i] + 1 : arr[i] - 1;
            if (find == arr[i + 1])
                break;
            ans++;
            int neighbor = arr[i + 1];
            swap(arr, i + 1, pos[find]);
            swap(pos, find, neighbor);
            i = pos[neighbor] % 2 == 0 ? pos[neighbor] : pos[neighbor] - 1;
        }
        return ans;
    }

    void swap(int[] arr, int src, int dest) {
        int t = arr[src];
        arr[src] = arr[dest];
        arr[dest] = t;
    }
}