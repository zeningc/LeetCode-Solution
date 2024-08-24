class Solution:
    def minInterval(self, intervals: List[List[int]], queries: List[int]) -> List[int]:
        # [l, r] inclusive intervals
        # size is num ints in it

        # for each query q, return the size of the smallest interval that contains q

        # if we sort intervals by start, then end decreasing that gets us close... but not
        # close enough necessarily

        # [ A               ]
        #       [ B   ]
        #        [ C]
        # 1     2 3   4  5
        # A     B C   B  A

        # from start of A until start of B, A is the best interval. We can find queries in that range and assign them
        # to A
        #
        # then start of B to start of C: find queries and assign them

        # so we want queries sorted by time, QlogQ and logQ space with quicksort
        
        # heapsort?
        #     > push intervals starting <= q
        #     > pop while top interval ends after q
        #     > order queue by length of the interval
        #     > so answer for each q after repairing RI is the top of the heap, or -1 if heap is empty

        # push <= q: can simulate with iterating through a sorted list
        # pop > q: can't do this arbitrarily with a pointer, need a heap for this

        ans = [-1]*len(queries)

        intervals.sort()
        qidxs = list(range(len(queries)))
        qidxs.sort(key=lambda i: queries[i])

        k = 0 # next interval index to push
        ivals = [] # (size, start, end) for all intervals containing next query, min heap by size
        for i in qidxs:
            q = queries[i]

            while ivals and ivals[0][2] < q: heappop(ivals)
            
            while k < len(intervals) and intervals[k][0] <= q:
                if intervals[k][1] >= q:
                    # skip intervals that start later, but have already ended
                    # (we'd pop them to maintain RI anyway)
                    heappush(ivals, (intervals[k][1]-intervals[k][0]+1, intervals[k][0], intervals[k][1]))
                k += 1

            if ivals:
                ans[i] = ivals[0][0]

        return ans