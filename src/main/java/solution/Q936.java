package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Stamping The Sequence",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/stamping-the-sequence/"
)
public class Q936 {
    /**
     * Time:  O(N ^ 2 * M) ? it'a actually much faster than this
     * Space: O(N)
     * */
    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length();
        int n = target.length();
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();
        List<Integer> steps = new ArrayList<>();
        boolean[] done = new boolean[n - m + 1];

        while(true) {
            boolean flip = false;
            for(int i = 0; i < n - m + 1; i++) {
                if(done[i]) {
                    continue;
                }
                if(match(s, t, i)) {
                    for(int j = 0; j < m; j++) {
                        t[i + j] = '?';
                    }
                    done[i] = true;
                    flip = true;
                    steps.add(i);
                }
            }

            if(!flip) {
                break;
            }
        }

        for(int i = 0; i < n; i++) {
            if(t[i] != '?') {
                return new int[0];
            }
        }

        Collections.reverse(steps);
        return steps.stream().mapToInt(a -> a.intValue()).toArray();
    }

    private boolean match(char[] s, char[] t, int st) {
        int n = s.length;

        for(int i = 0; i < n; i++) {
            if(t[st + i] != '?' && s[i] != t[st + i]) {
                return false;
            }
        }

        return true;
    }
}
