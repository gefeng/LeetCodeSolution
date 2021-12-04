package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "RLE Iterator",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/rle-iterator/"
)
public class Q900 {
    /**
     * Time:  O(N) amortized O(1) for next()
     * Space: O(1)
     * */
    int[] enc;
    int pos;
    public Q900(int[] encoding) {
        enc = encoding;
        pos = 0;
    }

    public int next(int n) {
        int ret = 0;
        while(pos < enc.length && n > 0) {
            int cnt = enc[pos];
            int val = enc[pos + 1];

            if(n >= cnt) {
                enc[pos] = 0;
                ret = enc[pos + 1];
                pos += 2;
                n -= cnt;
            } else {
                enc[pos] -= n;
                ret = enc[pos + 1];
                n = 0;
            }
        }

        if(n == 0) {
            return ret;
        }
        return -1;
    }
}
