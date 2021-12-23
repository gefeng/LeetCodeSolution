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
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public List<String> ambiguousCoordinates(String s) {
        List<String> ans = new ArrayList<>();
        s = s.substring(1, s.length() - 1);
        int n = s.length();

        for(int i = 0; i < n; i++) {
            List<String> l = solve(s.substring(0, i + 1));
            List<String> r = solve(s.substring(i + 1, n));

            if(l.isEmpty() || r.isEmpty()) continue;

            for(String s1 : l) {
                for(String s2 : r) {
                    ans.add("(" + s1 + ", " + s2 + ")");
                }
            }
        }

        return ans;
    }

    private List<String> solve(String s) {
        int n = s.length();
        List<String> ret = new ArrayList<>();
        if(n == 0) return ret;

        for(int i = 0; i < n; i++) {
            String l = s.substring(0, i + 1);
            String r = s.substring(i + 1, n);

            if(r.isEmpty()) {
                if(isValidInteger(l)) ret.add(l);
            } else {
                if(isValidInteger(l) && isValidFraction(r)) {
                    ret.add(l + "." + r);
                }
            }
        }

        return ret;
    }

    private boolean isValidInteger(String s) {
        int n = s.length();

        if(s.charAt(0) == '0' && n > 1) return false;
        return true;
    }

    private boolean isValidFraction(String s) {
        int n = s.length();
        if(s.charAt(n - 1) == '0') return false;
        return true;
    }
}
