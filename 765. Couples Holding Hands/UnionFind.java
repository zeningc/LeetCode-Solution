class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i + 1 < n; i += 2) {
            uf.union(i, i + 1);
            uf.union(row[i], row[i + 1]);
        }

        Map<Integer, Integer> grp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            grp.put(uf.find(i), grp.getOrDefault(uf.find(i), 0) + 1);
        }
        int ans = 0;
        for (int key : grp.keySet())
            ans += (grp.get(key) / 2) - 1;
        return ans;
    }
}

class UnionFind {
    int count;
    int[] parents;
    int[] rank;

    public UnionFind(int n) {
        this.count = n;
        this.parents = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parents[i] = i;
            this.rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x != parents[x]) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    public void union(int x, int y) {
        if (connected(x, y)) {
            return;
        }
        int rootX = find(x);
        int rootY = find(y);
        if (rank[rootY] > rank[rootX]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parents[rootY] = rootX;
        rank[rootX] += 1;
        count--;
    }
}
