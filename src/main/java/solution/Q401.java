package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Binary Watch",
        difficulty = QDifficulty.EASY,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/binary-watch/"
)
public class Q401 {
    /**
     * To count set bits, can use Integer.bitCount()
     * Time:  O(1)
     * Space: O(1)
     * */
    private static final int[] H = new int[] {1, 2, 4, 8};
    private static final int[] M = new int[] {1, 2, 4, 8, 16, 32};
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();

        for(int i = 0; i < 16; i++) {
            int cnt1 = cntBits(i);
            if(cnt1 > turnedOn) {
                continue;
            }

            for(int j = 0; j < 64; j++) {
                int cnt2 = cntBits(j);
                if(cnt1 + cnt2 == turnedOn) {
                    String t = getTime(i, j);
                    if(!t.isEmpty()) {
                        res.add(getTime(i, j));
                    }
                }
            }
        }

        return res;
    }

    private int cntBits(int m) {
        int cnt = 0;
        for(int i = 0; i < 6; i++) {
            if((m & (1 << i)) != 0) {
                cnt++;
            }
        }
        return cnt;
    }

    private String getTime(int m1, int m2) {
        int h = 0, m = 0;
        for(int i = 0; i < 4; i++) {
            if((m1 & (1 << i)) != 0) {
                h += H[i];
            }
        }

        if(h >= 12) {
            return "";
        }

        for(int i = 0; i < 6; i++) {
            if((m2 & (1 << i)) != 0) {
                m += M[i];
            }
        }

        if(m >= 60) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(h).append(":");
        if(m < 10) {
            sb.append("0");
        }
        sb.append(m);

        return sb.toString();
    }
}
