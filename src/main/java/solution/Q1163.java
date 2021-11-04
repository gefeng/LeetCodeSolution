package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Last Substring in Lexicographical Order",
        difficulty = QDifficulty.HARD,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/last-substring-in-lexicographical-order/"
)
public class Q1163 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String lastSubstring(String s) {
        int n = s.length();

        char max = 'a';
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c > max) {
                max = c;
            }
        }

        int st1 = 0;
        int st2 = 0;
        while(s.charAt(st1) != max) {
            st1++;
        }
        st2 = st1 + 1;
        while(st2 < n && s.charAt(st2) != max) {
            st2++;
        }

        int cand = st1;

        for(int i = st1 + 1, j = st2 + 1; j < n; i++, j++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);

            if(c1 != c2) {
                cand = c1 > c2 ? st1 : st2;
                st1 = cand;

                st2 += 1;
                while(st2 < n && s.charAt(st2) != max) {
                    st2++;
                }

                i = st1;
                j = st2;
            }
        }

        return s.substring(cand, n);
    }
}
