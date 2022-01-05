package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Set Intersection Size At Least Two",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/set-intersection-size-at-least-two/"
)
public class Q757 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int[] ans = new int[n * 2 + 5];

        Set<Integer> set = new HashSet<>();
        int p = 0;
        for(int i = 0; i < n; i++) {
            int[] cur = intervals[i];

            int rem = 2;

            for(int x : set) {
                if(x >= cur[0] && x <= cur[1]) rem--;
            }

            for(int j = cur[1]; j >= cur[0] && rem > 0; j--) {
                if(set.contains(j)) continue;
                rem--;
                set.add(j);
            }
        }

        return set.size();
    }
}
