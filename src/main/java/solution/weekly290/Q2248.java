package solution.weekly290;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Intersection of Multiple Arrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-290/problems/intersection-of-multiple-arrays/"
)
public class Q2248 {
    /**
     * Time:  O(M * N)
     * Space: O(N)
     * */
    public List<Integer> intersection(int[][] nums) {
        Set<Integer> cur = new HashSet<>();
        Set<Integer> nxt = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;

        for(int x : nums[0]) {
            cur.add(x);
        }

        for(int i = 1; i < n; i++) {
            nxt = new HashSet<>();
            for(int x : nums[i]) {
                if(cur.contains(x)) {
                    nxt.add(x);
                }
            }

            cur = nxt;
        }

        ans = new ArrayList<>(cur);
        Collections.sort(ans);

        return ans;
    }
}
