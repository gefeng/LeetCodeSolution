package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Design an Ordered Stream",
        difficulty = QDifficulty.EASY,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-an-ordered-stream/"
)
public class Q1656 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private String[] s;
    private int i;
    private int n;
    public Q1656(int n) {
        this.s = new String[n];
        this.i = 0;
        this.n = n;
    }

    public List<String> insert(int idKey, String value) {
        List<String> ret = new ArrayList<>();

        s[idKey - 1] = value;

        while(i < n && s[i] != null) {
            ret.add(s[i++]);
        }

        return ret;
    }
}
