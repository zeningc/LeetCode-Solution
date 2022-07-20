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
    public int read(char[] buf, int n) {
        int left = n;
        int readCnt = -1;
        int idx = 0;
        while (left > 0 && readCnt != 0)    {
            readCnt = read4(buf4);
            for (int i = 0; i < Math.min(readCnt, left); i++)
                buf[idx++] = buf4[i];
            left -= Math.min(left, readCnt);
        }
        
        return n - left;
    }
}
