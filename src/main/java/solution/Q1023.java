package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Camelcase Matching",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/camelcase-matching/"
)
public class Q1023 {
    /**
     * Time:  O(N * (N + L))
     * Space: O(N)
     * */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();

        for(String q : queries) {
            ans.add(isSubseq(pattern, q) && isMatch(pattern, q));
        }

        return ans;
    }

    private boolean isSubseq(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int i = 0, j = 0;
        while(i < m && j < n) {
            if(s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == m;
    }

    private boolean isMatch(String s1, String s2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for(int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                sb1.append(c);
            }
        }

        for(int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                sb2.append(c);
            }
        }

        return sb1.toString().equals(sb2.toString());
    }
}
