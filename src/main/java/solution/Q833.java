package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Find And Replace in String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/find-and-replace-in-string/"
)
public class Q833 {
    /**
     * Time:  O(M * logM + N)
     * Space: O(M + N)
     * */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int m = indices.length;

        int[][] pairs = new int[m][2];
        for(int i = 0; i < m; i++) {
            pairs[i][0] = indices[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        for(int i = 0, j = 0; i < n; i++) {
            if(j < m && i != pairs[j][0]) {
                sb.append(s.charAt(i));
            } else {
                if(j == m) {
                    sb.append(s.charAt(i));
                    continue;
                }
                int idx = pairs[j][0];
                String o = sources[pairs[j][1]];
                String t = targets[pairs[j][1]];

                boolean ok = true;
                int k = 0;
                for(; k < o.length() && k < n; k++) {
                    if(o.charAt(k) != s.charAt(idx + k)) {
                        ok = false;
                        break;
                    }
                }
                ok &= k == o.length();
                if(ok) {
                    sb.append(t);
                    i += o.length() - 1;
                } else {
                    sb.append(s.charAt(i));
                }
                j++;
            }
        }

        return sb.toString();
    }
}
