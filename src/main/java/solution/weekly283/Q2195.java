package solution.weekly283;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeSet;

@Problem(
        title = "Append K Integers With Minimal Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-283/problems/append-k-integers-with-minimal-sum/"
)
public class Q2195 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long minimalKSum(int[] nums, int k) {
        long ans = 0;
        TreeSet<Integer> set = new TreeSet<>();

        for(int x : nums) {
            set.add(x);
        }

        long pre = 0;
        for(int x : set) {
            long d = Math.min(x - pre - 1, k);
            ans += (pre + 1 + pre + d) * d / 2;
            k -= d;
            pre = x;
        }

        return ans += (pre + 1 + pre + k) * k / 2;
    }
}
