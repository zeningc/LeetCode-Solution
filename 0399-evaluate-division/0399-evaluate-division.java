class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair<String, Double>>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++)  {
            List<String> equation = equations.get(i);
            double ratio = values[i];
            String a = equation.get(0);
            String b = equation.get(1);
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(new Pair<String, Double>(b, ratio));
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(new Pair<String, Double>(a, (double)1.0 / ratio));
        }
        
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++)    {
            List<String> query = queries.get(i);
            String source = query.get(0);
            String dest = query.get(1);
            if (!graph.containsKey(source) || !graph.containsKey(dest)) {
                ans[i] = -1.0;
                continue;
            }
            Set<String> vis = new HashSet<>();
            Deque<Pair<String, Double>> q = new LinkedList<>();
            q.offer(new Pair<String, Double>(source, (double)1.0));
            while (!q.isEmpty())    {
                Pair<String, Double> cur = q.poll();
                String u = cur.getKey();
                
                if (vis.contains(u))
                    continue;
                
                vis.add(u);
                double w = cur.getValue();
                
                if (u.equals(dest))  {
                    ans[i] = w;
                    break;
                }
                
                for (Pair<String, Double> nxt : graph.getOrDefault(u, new ArrayList<>()))   {
                    String v = nxt.getKey();
                    if (vis.contains(v))
                        continue;
                    
                    double nxtW = nxt.getValue();
                    q.offer(new Pair<String, Double>(v, w * nxtW));
                }
            }
            if (!vis.contains(dest))
                ans[i] = -1.0;
        }
        
        
        return ans;
    }
}