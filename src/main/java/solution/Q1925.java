package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Count Square Sum Triples",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-square-sum-triples/"
)
public class Q1925 {
    public int countTriples(int n) {
        int cnt = 0;
        Set<Integer> sqr = new HashSet<>();
        for(int i = 1; i <= n; i++) {
            sqr.add(i * i);
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(sqr.contains(i * i + j * j)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
