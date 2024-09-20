class Solution:
    def getMaxGridHappiness(self, m: int, n: int, introvertsCount: int, extrovertsCount: int) -> int:
        mask = 0
        for i in range(n):
            mask <<= 2
            mask |= 0b11

        # prev to save the last n cells , from the top cell to the left cell
        # 0b11 : empty cell, 0b01: intro, 0b10: extro
        @cache
        def dp(index, intro, extro, prev):
            if index == m * n:
                return 0
            i, j = index // n, index % n
            res = dp(index+1, intro, extro, ((prev << 2) | 0b11) & mask)

            left, top = prev & 0b11, (prev >> (2*(n-1))) & 0b11
            if intro < introvertsCount:
                cur = 120
                if j > 0 and left == 0b01:
                    cur -= 60
                elif j > 0 and left == 0b10:
                    cur -= 10
                if i > 0 and top == 0b01:
                    cur -= 60
                elif i > 0 and top == 0b10:
                    cur -= 10
                res = max(res, cur + dp(index+1, intro+1, extro, ((prev << 2) | 0b01) & mask))

            if extro < extrovertsCount:
                cur = 40
                if j > 0 and left == 0b01:
                    cur -= 10
                elif j > 0 and left == 0b10:
                    cur += 40
                if i > 0 and top == 0b01:
                    cur -= 10
                elif i > 0 and top == 0b10:
                    cur += 40
                res = max(res, cur + dp(index+1, intro, extro+1, ((prev << 2) | 0b10) & mask))

            return res

        return dp(0, 0, 0,  0)
