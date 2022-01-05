package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Problem(
        title = "Special Binary String",
        difficulty = QDifficulty.HARD,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/special-binary-string/"
)
public class Q761 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public String makeLargestSpecial(String s) {
        int n = s.length();

        List<String> specials = new ArrayList<>();
        for(int i = 0; i < n;) {
            int cnt = 0, j = i;
            for(; j < n && (i == j || cnt > 0); j++) {
                if(s.charAt(j) == '1') cnt++;
                else cnt--;
            }

            specials.add("1" + makeLargestSpecial(s.substring(i + 1, j - 1)) + "0");

            i = j;
        }

        Collections.sort(specials, Comparator.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for(String spe : specials) {
            sb.append(spe);
        }

        return sb.toString();
    }
}
