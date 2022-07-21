class Solution {
    public int getSum(int a, int b) {
        int ans = 0;
        int c = 0;
        int bitSum = 0;
        for (int i = 0; i < 32; i++)    {
            int aBit = (a & (1 << i)) == 0 ? 0 : 1;
            int bBit = (b & (1 << i)) == 0 ? 0 : 1;
            if (aBit == 0 && bBit == 0) {
                if (c == 1) {
                    bitSum = 1;
                    c = 0;
                }
                else
                    bitSum = 0;
            }
            else if (aBit == 0 && bBit == 1 || aBit == 1 && bBit == 0)    {
                if (c == 1) {
                    bitSum = 0;
                }
                else    {
                    bitSum = 1;
                }
            }
            else if (aBit == 1 && bBit == 1)    {
                if (c == 1) {
                    bitSum = 1;
                }
                else {
                    c = 1;
                    bitSum = 0;
                }
            }
            
            ans |= (bitSum << i);
        }
        
        return ans;
    }
}
