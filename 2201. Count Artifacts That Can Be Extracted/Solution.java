class Solution {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[][] digged = new boolean[1001][1001];
        int ans = 0;
        for (int[] d : dig) {
            digged[d[0]][d[1]] = true;
        }
        for (int[] artifact : artifacts) {
            int r1 = artifact[0];
            int c1 = artifact[1];
            int r2 = artifact[2];
            int c2 = artifact[3];
            int flag = 0;
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    if (!digged[i][j]) {
                        flag = 1;
                        break;
                    }
                }
            }

            if (flag == 0) {
                ans++;
            }
        }

        return ans;
    }
}