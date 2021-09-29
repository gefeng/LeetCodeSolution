package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Diagonals Traverse II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/diagonal-traverse-ii/"
)
public class Q1424 {
    /**
     * Traverse will take too long because the given structure is sparse.
     * Instead we can collect diagonal one by one.
     * The cells on the same diagonal have the property that r + c == C where C is a constant number.
     *
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();
        int maxc = 0;
        for(int i = 0; i < nums.size(); i++) {
            maxc = Math.max(maxc, nums.get(i).size());
        }

        List<Integer>[] diag = new List[n + maxc - 1];
        for(int i = 0; i < diag.length; i++) {
            diag[i] = new ArrayList<>();
        }

        int total = 0;
        for(int i = 0; i < n; i++) {
            List<Integer> r = nums.get(i);

            for(int j = 0; j < r.size(); j++) {
                diag[i + j].add(r.get(j));
                total++;
            }
        }

        int[] ans = new int[total];
        int idx = 0;
        for(int i = 0; i < diag.length; i++) {
            int sz = diag[i].size();
            for(int j = sz - 1; j >= 0; j--) {
                ans[idx++] = diag[i].get(j);
            }
        }

        return ans;
    }
}
