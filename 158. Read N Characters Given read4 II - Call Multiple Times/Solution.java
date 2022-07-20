/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4); 
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] buf4 = new char[4];
    int p = 0;
    int readCnt = 0;
    public int read(char[] buf, int n) {
        int left = n;
        int idx = 0;
        int consume = -1;
        while (left > 0 && consume != 0)    {
            if (p < readCnt)  {
                consume = Math.min(left, readCnt - p);
            }
            else    {
                p = 0;
                readCnt = read4(buf4);
                consume = Math.min(readCnt, left);
            }
            for (int i = 0; i < consume; i++)   {
                    buf[idx++] = buf4[p++];
            }
            left -= consume;
        }
        return n - left;
    }
}
