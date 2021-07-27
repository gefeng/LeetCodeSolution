package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Arithmetic Subarrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/arithmetic-subarrays/"
)
public class Q1630 {
    /**
     * Time:  O(M * (N + logN))
     * Space: O(N)
     * */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        int n = nums.length;
        List<Boolean> res = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            int[] copy = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(copy);
            if(copy.length < 2) {
                res.add(false);
            }

            int diff = copy[1] - copy[0];
            boolean ok = true;
            for(int j = 2; j < copy.length; j++) {
                if(copy[j] - copy[j - 1] != diff) {
                    ok = false;
                    break;
                }
            }
            res.add(ok);
        }

        return res;
    }
}
