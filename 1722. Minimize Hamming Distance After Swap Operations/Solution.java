class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        UnionFind uf = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            int u = swap[0];
            int v = swap[1];
            uf.union(u, v);
        }
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!map.containsKey(uf.find(i))) {
                map.put(uf.find(i), new LinkedList<>());
            }
            map.get(uf.find(i)).add(i);
        }

        for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            Map<Integer, Integer> freq1 = new HashMap<>();
            Map<Integer, Integer> freq2 = new HashMap<>();
            for (int v : e.getValue()) {
                freq1.put(source[v], freq1.getOrDefault(source[v], 0) + 1);
                freq2.put(target[v], freq2.getOrDefault(target[v], 0) + 1);
            }
            for (int key : freq1.keySet()) {
                if (freq1.get(key) > freq2.getOrDefault(key, 0)) {
                    ans += Math.abs(freq1.get(key) - freq2.getOrDefault(key, 0));
                }
            }

        }

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