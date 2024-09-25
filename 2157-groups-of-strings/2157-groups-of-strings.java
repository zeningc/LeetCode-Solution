class Solution {
    public int[] groupStrings(String[] words) {
        int n = words.length;
        Map<Integer, Integer> stateCnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int state = 0;
            for (char c : words[i].toCharArray())
                state |= (1 << (c - 'a'));
            stateCnt.put(state, stateCnt.getOrDefault(state, 0) + 1);
        }
        UnionFind uf = new UnionFind(stateCnt.keySet());
        for (int state : stateCnt.keySet())    {
            for (int i = 0; i < 26; i++)    {
                if ((state & (1 << i)) != 0)    {
                    if (stateCnt.containsKey((state ^ (1 << i))))
                        uf.union(state, (state ^ (1 << i)));
                    
                    
                    for (int j = 0; j < 26; j++)    {
                        if ((state & (1 << j)) == 0 && stateCnt.containsKey(((state | (1 << j)) ^ (1 << i))))
                            uf.union(state, ((state | (1 << j)) ^ (1 << i)));
                    }
                }
                else    {
                    if (stateCnt.containsKey((state | (1 << i))))
                        uf.union(state, (state | (1 << i)));
                }
            }
        }
        
        int maxSize = 1;
        Map<Integer, Integer> grp = new HashMap<>();
        for (int state : stateCnt.keySet()) {
            int root = uf.find(state);
            grp.put(root, grp.getOrDefault(root, 0) + stateCnt.get(state));
            maxSize = Math.max(maxSize, grp.get(root));
        }
        
        return new int[] {grp.size(), maxSize};
    }
}

class UnionFind {
    Map<Integer, Integer> parents;
    Map<Integer, Integer> size;
    
    public UnionFind(Set<Integer> states) {
        parents = new HashMap<>();
        size = new HashMap<>();
        for (int state : states)    {
            parents.put(state, state);
            size.put(state, 1);
        }
    }
    
    public int find(int x) {
        if (parents.get(x) != x)
            parents.put(x, find(parents.get(x)));
        return parents.get(x);
    }
    
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    public void union(int x, int y) {
        if (connected(x, y))
            return;
        int rootX = find(x);
        int rootY = find(y);
        if (size.get(rootX) < size.get(rootY))  {
            int t = rootX;
            rootX = rootY;
            rootY = t;
        }
        
        parents.put(rootY, rootX);
        size.put(rootX, size.get(rootX) + size.get(rootY));
    }
}