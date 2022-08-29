class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        Map<Pair, Integer> map1 = new HashMap<>();
        int m1 = mat1.length;
        int n1 = mat1[0].length;
        int m2 = mat2.length;
        int n2 = mat2[0].length;
        
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n1; j++)
                if (mat1[i][j] != 0)
                    map1.put(new Pair(i, j), mat1[i][j]);
        
        int[][] ans = new int[m1][n2];
        for (Pair p : map1.keySet())    {
            int x = p.x;
            int y = p.y;
            int val = map1.get(p);
            for (int i = 0; i < n2; i++)
                ans[x][i] += val * mat2[y][i];
        }
        
        return ans;
    }
}

class Pair  {
    int x;
    int y;
    
    public Pair(int x, int y)   {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof Pair))
            return false;
        
        Pair p = (Pair)o;
        
        return p.x == x && p.y == y;
    }
    
    @Override
    public int hashCode() {
        return x * 17 + y;
    }
}
