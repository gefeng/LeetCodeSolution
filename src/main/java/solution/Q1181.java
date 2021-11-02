package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Before and After Puzzle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/before-and-after-puzzle/"
)
public class Q1181 {
    /**
     * Time:  O(N ^ 2 * L * logN)
     * Space: O(N * N * L)
     * */
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        int n = phrases.length;
        Set<String> ans = new TreeSet<>();
        List<String[]> l = new ArrayList<>();

        for(String phr : phrases) {
            l.add(phr.split(" "));
        }

        for(int i = 0; i < n; i++) {
            String[] p1 = l.get(i);
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                String[] p2 = l.get(j);
                if(p1[p1.length - 1].equals(p2[0])) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.join(" ", p1));

                    if(p2.length > 1) {
                        sb.append(" ").append(String.join(" ", Arrays.copyOfRange(p2, 1, p2.length)));
                    }
                    ans.add(sb.toString());
                }
            }
        }

        return new ArrayList<>(ans);
    }
}
