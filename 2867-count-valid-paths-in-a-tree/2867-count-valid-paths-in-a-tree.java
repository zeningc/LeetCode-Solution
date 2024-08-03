class Solution {
    Map<Integer, Boolean> isPrimeCache;
    
    public long countPaths(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n + 1);
        isPrimeCache = new HashMap<>();
        Map<Integer, List<Integer>> primeLink = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (isPrime(u) || isPrime(v))   {
                if (isPrime(u) && !isPrime(v))
                    primeLink.computeIfAbsent(u, x -> new ArrayList<>()).add(v);
                else if (isPrime(v) && !isPrime(u))
                    primeLink.computeIfAbsent(v, x -> new ArrayList<>()).add(u);
                continue;
            }
            uf.union(u, v);
        }
        
        long ans = 0;
        for (int i = 1; i <= n; i++)    {
            if (!isPrime(i) || !primeLink.containsKey(i))
                continue;
            List<Integer> links = primeLink.get(i);
            long total = 0;
            for (int link : links)
                total += uf.getSize(link);
            long cnt = 0;
            ans += total;
            for (int link : links)
                cnt += uf.getSize(link) * (total - uf.getSize(link));
            ans += cnt / 2;
        }
        
        return ans;
    }
    
    boolean isPrime(int number) {
        if (isPrimeCache.containsKey(number))
            return isPrimeCache.get(number);
        if (number < 2) {
            isPrimeCache.put(number, false);
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                isPrimeCache.put(number, false);
                return false;
            }
        }
        isPrimeCache.put(number, true);
        return true; // No factors found, number is prime
    }
}

class UnionFind {
    int[] parent;
    int[] size;
    int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            rank[i] = 1;
        }
    }
    
    private int find(int u) {
        if (parent[u] != u)
            parent[u] = find(parent[u]);
        return parent[u];
    }
    
    private boolean connected(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        return rootU == rootV;
    }
    
    public void union(int u, int v)  {
        if (connected(u, v))
            return;
        int rootU = find(u);
        int rootV = find(v);
        
        if (rank[rootU] < rank[rootV])  {
            int t = rootU;
            rootU = rootV;
            rootV = t;
        }
        
        parent[rootV] = rootU;
        rank[rootU]++;
        size[rootU] += size[rootV];
    }
    
    public int getSize(int x)    {
        return size[find(x)];
    }
}