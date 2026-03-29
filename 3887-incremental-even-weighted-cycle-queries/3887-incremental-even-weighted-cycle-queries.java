class Solution {
    public int numberOfEdgesAdded(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int ans = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int ru = uf.find(u);
            int rv = uf.find(v);
            if (ru == rv)   {
                if ((uf.parity[u] ^ uf.parity[v] ^ w) == 0)
                    ans++;
            }
            else {
                uf.parent[ru] = rv;
                uf.parity[ru] = uf.parity[u] ^ uf.parity[v] ^ w;
                ans++;
            }

        }
        
        return ans;
    }
}

class UnionFind {
    int[] parent;
    int[] parity;

    public UnionFind(int n) {
        parent = new int[n];
        parity = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }


    public int find(int x)    {
        if (parent[x] != x) {
            int oldP = parent[x];
            parent[x] = find(parent[x]);
            parity[x] ^= parity[oldP];
        }

        return parent[x];
    }
}