class Solution {
    public String nearestPalindromic(String n) {
        long nNum = Long.valueOf(n);
        if (nNum < 10)
            return String.valueOf(nNum - 1);
        int posToFlip = n.length() % 2 == 0 ? n.length() / 2 - 1 : n.length() / 2;
        String toFlipStr = n.substring(0, posToFlip + 1);
        long toFlipNum = Long.valueOf(toFlipStr);
        String a = getFlipNum(n, toFlipStr, toFlipNum, posToFlip, -1);
        String b = getFlipNum(n, toFlipStr, toFlipNum, posToFlip, 1);
        String c = getFlipNum(n, toFlipStr, toFlipNum, posToFlip, 0);
        
        return getAns(n, a, getAns(n, b, c));
    }
    
    String getFlipNum(String n, String toFlipStr, long toFlipNum, int posToFlip, int delta) {
        String a = null;
        if (delta == -1 && isPowerOfTen(toFlipStr))    {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n.length() - 1; i++)
                sb.append('9');
            return sb.toString();
        }
        if (delta == 1 && isPowerOfTenMinusOne(toFlipStr)) {
            StringBuilder sb = new StringBuilder();
            sb.append('1');
            for (int i = 0; i < n.length() - 1; i++)
                sb.append('0');
            sb.append('1');
            return sb.toString();
        }
        toFlipNum += delta;
        String modifiedToFlipStr = String.valueOf(toFlipNum);
        return flip(modifiedToFlipStr, n.length() % 2 == 0 ? posToFlip : posToFlip - 1);
    }
    
    boolean isPowerOfTen(String toFlipStr) {
        if (toFlipStr.charAt(0) != '1')
            return false;
        for (int i = 1; i < toFlipStr.length(); i++)
            if (toFlipStr.charAt(i) != '0')
                return false;
        return true;
    }
    
    boolean isPowerOfTenMinusOne(String toFlipStr)  {
        for (char c : toFlipStr.toCharArray())
            if (c != '9')
                return false;
        return true;
    }
    
    String getAns(String n, String a, String b) {
        long nNum = Long.valueOf(n);
        long aNum = Long.valueOf(a);
        long bNum = Long.valueOf(b);
        if (nNum == aNum)
            return b;
        if (nNum == bNum)
            return a;
        if (Math.abs(nNum - aNum) < Math.abs(nNum - bNum))
            return a;
        if (Math.abs(nNum - aNum) > Math.abs(nNum - bNum))
            return b;
        if (aNum < bNum)
            return a;
        return b;
    }
    
    String flip(String numStr, int pos)    {
        StringBuilder partToReverse = new StringBuilder(numStr.substring(0, pos + 1));
        partToReverse.reverse();
        return numStr + partToReverse.toString();
    }
}