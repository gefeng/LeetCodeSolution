package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Ambiguous Coordinates",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/ambiguous-coordinates/"
)
public class Q816 {
    /*
    * split to 2 parts will be easier to construct
    * */
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();

        for(int cut = 2; cut < n - 1; cut++) {
            String x = s.substring(1, cut);     // O(n)
            String y = s.substring(cut, n - 1); // O(n)
            make(x, y, ans);                    // O(n^3)
        }

        return ans;
    }

    private void make(String s1, String s2, List<String> ans) {
        int m = s1.length();
        int n = s2.length();
        for(int i = 0; i < m; i++) {
            StringBuilder xb = new StringBuilder(s1.substring(0, i)).append(i == 0 ? "" : ".").append(s1, i, m);
            if(!valid(xb)) {
                continue;
            }
            for(int j = 0; j < n; j++) {
                StringBuilder yb = new StringBuilder(s2.substring(0, j)).append(j == 0 ? "" : ".").append(s2, j, n);
                if(!valid(yb)) {
                    continue;
                }

                StringBuilder coord = new StringBuilder("(").append(xb).append(", ").append(yb).append(")");
                ans.add(coord.toString());
            }
        }
    }

    private boolean valid(StringBuilder s) {
        int n = s.length();
        if(n == 1) {
            return true;
        }

        int dot = s.indexOf(".");
        if(s.charAt(0) == '0' && dot != 1) {
            return false;
        }
        if(dot > 0 && s.charAt(n - 1) == '0') {
            return false;
        }

        return true;
    }
}
