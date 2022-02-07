package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shortest Way to Form String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/shortest-way-to-form-string/"
)
public class Q1055 {
    /**
     * Time:  O(M * N)
     * Space: O(1);
     * */
    public int shortestWay(String source, String target) {
        int m = source.length();
        int n = target.length();
        int ans = 0;

        for(int i = 0; i < n; ) {
            int len = subseq(source, target, i);
            if(len == 0) return -1;

            i += len;
            ans++;
        }

        return ans;
    }

    private int subseq(String s, String t, int st) {
        int m = s.length();
        int n = t.length();
        int i = 0, j = st;
        for(; i < m && j < n; ) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return j - st;
    }
}
