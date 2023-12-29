import heapq

class Solution:
    def modifiedGraphEdges(self, n, edges, source, destination, target):
        adjs = [{} for _ in range(n)]

        for edge in edges:
            adjs[edge[0]][edge[1]] = edge[2]
            adjs[edge[1]][edge[0]] = edge[2]

        distTo = [float('inf')] * n
        distTo[source] = 0

        pq = [(0, source)]
        heapq.heapify(pq)

        self.dijkstra(adjs, distTo, pq)

        if distTo[destination] == target:
            return self.fill(edges)
        elif distTo[destination] < target:
            return []

        for edge in edges:
            if edge[2] == -1:
                edge[2] = 1
                adjs[edge[0]][edge[1]] = 1
                adjs[edge[1]][edge[0]] = 1

                pq = [(distTo[edge[0]], edge[0]), (distTo[edge[1]], edge[1])]

                self.dijkstra(adjs, distTo, pq)

                if distTo[destination] == target:
                    return self.fill(edges)
                elif distTo[destination] < target:
                    edge[2] += target - distTo[destination]
                    adjs[edge[0]][edge[1]] = edge[2]
                    adjs[edge[1]][edge[0]] = edge[2]
                    return self.fill(edges)

        return []

    def fill(self, edges):
        for edge in edges:
            if edge[2] == -1:
                edge[2] = int(2e9)
        return edges

    def dijkstra(self, adjs, distTo, pq):
        while pq:
            curr_dist, curr = heapq.heappop(pq)

            for next_node, weight in adjs[curr].items():
                if weight > 0:
                    if distTo[next_node] - weight > distTo[curr]:
                        distTo[next_node] = distTo[curr] + weight
                        heapq.heappush(pq, (distTo[next_node], next_node))