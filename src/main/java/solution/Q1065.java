package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Index Pairs of a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/index-pairs-of-a-string/"
)
public class Q1065 {
    /**
     * Time:  O(M * N + sort(res))
     * Space: O(M * N)
     * */
    public int[][] indexPairs(String text, String[] words) {
        int n = text.length();
        List<int[]> res = new ArrayList<>();

        for(String w : words) {
            int st = 0;
            while(st < n) {
                int l = text.indexOf(w, st);

                if(l < 0) {
                    break;
                }

                res.add(new int[] {l, l + w.length() - 1});

                st = l + 1;
            }
        }

        Collections.sort(res, (a, b) -> {
            if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        return res.toArray(new int[res.size()][]);
    }
}
