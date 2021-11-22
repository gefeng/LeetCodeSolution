package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "String Without AAA or BBB",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/string-without-aaa-or-bbb/"
)
public class Q984 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String strWithout3a3b(int a, int b) {
        if(a >= b) {
            return solve(a, b, 'a', 'b');
        } else {
            return solve(b, a, 'b', 'a');
        }
    }

    private String solve(int a, int b, char ca, char cb) {
        StringBuilder sb = new StringBuilder();

        while(a != b && a > 1 && b > 0) {
            sb.append(ca);
            sb.append(ca);
            a -= 2;
            sb.append(cb);
            b -= 1;
        }

        while(a > 0 || b > 0) {
            if(a > 0) {
                sb.append(ca);
                a -= 1;
            }
            if(b > 0) {
                sb.append(cb);
                b -= 1;
            }
        }

        return sb.toString();
    }
}
