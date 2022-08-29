class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft))
            return 0;
        int rx = topRight[0];
        int ry = topRight[1];
        int lx = bottomLeft[0];
        int ly = bottomLeft[1];
        
        if (lx == rx && ly == ry)
            return sea.hasShips(topRight, bottomLeft) ? 1 : 0;
        
        if (lx == rx)   {
            return countShips(sea, new int[] {lx, (ly + ry) / 2}, new int[] {lx, ly})
                + countShips(sea, new int[] {rx, ry}, new int[] {rx, (ly + ry) / 2 + 1});
        }
        
        if (ly == ry)   {
            return countShips(sea, new int[] {(lx + rx) / 2, ly}, new int[] {lx, ly})
                + countShips(sea, new int[] {rx, ry}, new int[] {(lx + rx) / 2 + 1, ry});
        }
        
        int midX = (lx + rx) / 2;
        int midY = (ly + ry) / 2;
        
        return countShips(sea, new int[] {rx, midY}, new int[] {midX + 1, ly})
            + countShips(sea, new int[] {rx, ry}, new int[] {midX + 1, midY + 1})
            + countShips(sea, new int[] {midX, midY}, new int[] {lx, ly})
            + countShips(sea, new int[] {midX, ry}, new int[] {lx, midY + 1});
    }
}
