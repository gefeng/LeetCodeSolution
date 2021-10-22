package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Reconstruct a 2-Row Binary Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix/"
)
public class Q1253 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = colsum.length;

        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(colsum[i] == 0) {
                r1.add(0);
                r2.add(0);
            } else if(colsum[i] == 2) {
                r1.add(1);
                r2.add(1);
                upper -= 1;
                lower -= 1;
            } else {
                if(upper >= lower) {
                    r1.add(1);
                    r2.add(0);
                    upper -= 1;
                } else {
                    r1.add(0);
                    r2.add(1);
                    lower -= 1;
                }
            }
        }


        if(upper == 0 && lower == 0) {
            ans.add(r1);
            ans.add(r2);
        }
        return ans;
    }
}
