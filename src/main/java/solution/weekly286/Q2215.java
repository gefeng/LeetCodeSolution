package solution.weekly286;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Find the Difference of Two Arrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-286/problems/find-the-difference-of-two-arrays/"
)
public class Q2215 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();

        for(int x : nums1) {
            set1.add(x);
        }
        for(int x : nums2) {
            set2.add(x);
        }
        for(int i = 0; i < 2; i++) {
            ans.add(new ArrayList<>());
        }

        for(int x : set1) {
            if(!set2.contains(x)) {
                ans.get(0).add(x);
            }
        }

        for(int x : set2) {
            if(!set1.contains(x)) {
                ans.get(1).add(x);
            }
        }

        return ans;
    }
}
