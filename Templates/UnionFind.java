
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
