class Solution {
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, List<Integer>> codeToRow = new HashMap<>();
        List<Integer> encodes = new ArrayList<>(m);
        int lowestBitCnt = 5;
        for (int i = 0; i < m; i++) {
            int encode = 0;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)    {
                    encode |= (1 << j);
                    cnt++;
                }
            }
            encodes.add(encode);
            if (encode == 0)
                return List.of(i);
            lowestBitCnt = Math.min(cnt, lowestBitCnt);
            if (!codeToRow.containsKey(encode))
                codeToRow.put(encode, new LinkedList<>());
            codeToRow.get(encode).add(i);
            
        }
        
        if (lowestBitCnt > 2)
            return new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            int encode = encodes.get(i);
            for (int state = 1; state < (1 << n); state++)  {
                if (!codeToRow.containsKey(state))
                    continue;
                if ((encode & state) == 0)  {
                    for (int k : codeToRow.get(state))  {
                        List<Integer> ans = new ArrayList<>(2);
                        ans.add(i);
                        ans.add(k);
                        Collections.sort(ans);
                        return ans;
                    }
                }
            }
        }
        
        return new LinkedList<>();
    }
}