public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        for (int i = 0; i < 16; i++)    {
            int b1 = (n >> i) & 1;
            int b2 = (n >> (31 - i)) & 1;
            if (b1 == b2)
                continue;
            if (b1 == 1)    
                n |= (1 << (31 - i));
            else
                n &= ~(1 << (31 - i));
                
            if (b2 == 1)
                n |= (1 << i);
            else
                n &= ~(1 << i);
        }
        return n;
    }
}
