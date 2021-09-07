package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Find a Value of a Mysterious Function Closest to Target",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/"
)
public class Q1521 {
    /**
     * Time:  O(N * log(max(arr)))
     * Space: O(log(max(arr)))
     * */
    public int closestToTarget(int[] arr, int target) {
        int res = Integer.MAX_VALUE;
        Set<Integer> s = new HashSet<>();

        for(int i = 0; i < arr.length; i++) {
            Set<Integer> ns = new HashSet<>();
            ns.add(arr[i]);

            for(int num : s) {
                ns.add(arr[i] & num);
            }

            for(int num : ns) {
                res = Math.min(res, Math.abs(num - target));
            }

            s = ns;
            System.out.println(s.size());
        }

        return res;
    }
}
