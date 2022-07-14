package solution.weekly301;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Move Pieces to Obtain a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/contest/weekly-contest-301/problems/move-pieces-to-obtain-a-string/"
)
public class Q2337 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean canChange(String start, String target) {
        int n = start.length();

        String s = start.replaceAll("_", "");
        String t = target.replaceAll("_", "");

        if(!s.equals(t)) {
            return false;
        }


        List<int[]> A = new ArrayList<>();
        List<int[]> B = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            char c1 = start.charAt(i);
            char c2 = target.charAt(i);

            if(c1 == 'L') {
                A.add(new int[] {0, i});
            } else if(c1 == 'R') {
                A.add(new int[] {1, i});
            }

            if(c2 == 'L') {
                B.add(new int[] {0, i});
            } else if(c2 == 'R') {
                B.add(new int[] {1, i});
            }
        }


        for(int i = 0; i < A.size(); i++) {
            int[] x = A.get(i);
            int[] y = B.get(i);

            if(x[0] == 0 && x[1] < y[1]) {
                return false;
            }

            if(x[0] == 1 && x[1] > y[1]) {
                return false;
            }
        }

        return true;
    }
}
