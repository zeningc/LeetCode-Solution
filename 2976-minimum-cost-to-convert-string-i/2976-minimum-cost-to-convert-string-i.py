class Solution:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:
        m = len(original)
        graph = [[inf]*26 for _ in range(26)]

        get_i = lambda ch: ord(ch) - ord("a")
        for i in range(m):
            p = get_i(original[i])
            q = get_i(changed[i])
            graph[p][q] = min(graph[p][q], cost[i])

        def shortestDistance(root):
            que = [[0, root]]
            visited = set()
            while que:
                cost, node = heappop(que)

                if node in visited: continue
                visited.add(node)

                if node != root:
                    graph[root][node] = cost

                for i in range(26):
                    if i not in visited and graph[node][i] != inf:
                        heappush(que, [cost + graph[node][i], i])

        for i in range(26):
            shortestDistance(i)

        cost = 0
        for i in range(len(source)):
            x = source[i]; y = target[i]
            if x != y:
                cost += graph[get_i(x)][get_i(y)]
                if cost == inf:
                    return -1
        return cost
